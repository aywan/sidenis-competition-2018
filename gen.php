<?php

$n = 550;

echo $n, \PHP_EOL;

$s = implode(' ', array_fill(0, $n, -10000000000)) . \PHP_EOL;
for ($i = 0; $i < $n; $i++) {
    echo $s;
}

$q = 100100;
echo $q, \PHP_EOL;

for ($i = 0; $i < $q; $i++) {
    echo '1 100 100 499 499', \PHP_EOL;
}
