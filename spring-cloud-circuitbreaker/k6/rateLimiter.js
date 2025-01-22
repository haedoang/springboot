import http from 'k6/http';
import { sleep, check } from 'k6';

export const options = {
    vus: 1, // Virtual Users
    iterations: 20, // 총 20번 요청
};

export default function () {
    const res = http.get('http://localhost:8080/rateLimiter');

    if (__ITER < 10) {
        // 처음 10개의 요청은 정상 상태를 검증
        check(res, {
            'is status 200': (r) => r.status === 200,
            'body contains "it works!"': (r) => r.body.includes('it works!'),
        });
    } else {
        // 이후 요청은 제한 상태를 검증
        check(res, {
            'is status 429': (r) => r.status === 429,
            'body contains "fallback"': (r) => r.body.includes('fallback'),
        });
    }
}