import http from 'k6/http';
import { sleep, check } from 'k6';

export default function () {
    const res = http.get('http://localhost:8080/bulkhead');

    check(res, {
        'is status 200': (r) => r.status === 200,
        'body contains "it works!"': (r) => r.body.includes('it works!'),
    });

    sleep(0.2);
}