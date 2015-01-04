#!/usr/bin/jjs

for (p in $ENV) {
    print(p + '=' + $ENV[p]);
}
