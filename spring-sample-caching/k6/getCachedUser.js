import http from 'k6/http';
import { check, sleep } from 'k6';

export let options = {
    vus: 100, // 1 user looping for 1 minute
    duration: '30s',

    thresholds: {
        http_req_duration: ['p(99) < 50'], // 99% of requests must complete below 1.5s
    },
};

export default function ()  {
    let getUser = http.get('http://localhost:8080/api/v1/users/user1/cached');

    check(getUser, {
        'users/user1/cached successfully': (resp) => resp.status == 200 ,
    });

    sleep(1);
};
