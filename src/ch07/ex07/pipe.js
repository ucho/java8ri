#!/usr/bin/jjs

var BufferedReader = java.io.BufferedReader;
var Executors = java.util.concurrent.Executors;
var InputStreamReader = java.io.InputStreamReader;
var OutputStreamWriter = java.io.OutputStreamWriter;
var PrintWriter = java.io.PrintWriter;
var ProcessBuilder = java.lang.ProcessBuilder;
var StringWriter = java.io.StringWriter;
var TimeUnit = java.util.concurrent.TimeUnit;

var pipe = function() {
    var ex = Executors.newCachedThreadPool();
    var proc_out;
    for (var i in arguments) {
        var proc = new ProcessBuilder(arguments[i].split(/\s+/)).start();
        if (proc_out) {
            var prev_out = proc_out;
            ex.execute(function() {
                var proc_in = new PrintWriter(new OutputStreamWriter(proc.getOutputStream()));
                var line;
                while ((line = prev_out.readLine()) != null) {
                    proc_in.println(line);
                }
                prev_out.close();
                proc_in.close();
            });
        }
        proc_out = new BufferedReader(new InputStreamReader(proc.getInputStream()));
    }
    ex.shutdown();
    ex.awaitTermination(1, TimeUnit.MINUTES);

    var result = new StringWriter();
    var result_in = new PrintWriter(result);
    var line;
    while ((line = proc_out.readLine()) != null) {
        result_in.println(line);
    }
    return result.toString();
};

var test = function() {
    var result = pipe('cat pipe.js', 'tail -n1');
    if (result !== 'test();\n') {
        print('unexpected result: ' + result);
        exit(1);
    }
};

test();
