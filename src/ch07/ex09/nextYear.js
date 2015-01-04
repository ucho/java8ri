#!/usr/bin/jjs

var scan_current_age = function() {
    if ($ARG[0]) {
        return $ARG[0];
    } else if ($ENV.AGE) {
        return $ENV.AGE;
    } else {
        return readLine('Your age: ');
    }
};

var print_next_age = function(age) {
    var age_num = parseInt(age, 10);
    if (isNaN(age_num) || age < 0) {
        print('invalid input: ' + age);
        exit(1);
    }
    var next_age = age_num + 1;
    print("Next year, you will be " + next_age);
};

print_next_age(scan_current_age());
