package org.practice.exceptionhandling;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class A {
    public ResponseObj getUserInfo() throws Exception {

        if (Math.random() < 0.7) {
            throw new Exception("Random failure occurred!");
        }
        return new ResponseObj("aaa", "bbb");
    }

    public ResponseObj getUserInfo(int i) throws Exception {
        if (Math.random() < 0.7) {
            throw new Exception("Random failure occurred!");
        }
        return new ResponseObj("111", "2222");
    }
}

@AllArgsConstructor
@NoArgsConstructor
@ToString
class ResponseObj {
    String a;
    String b;
}


