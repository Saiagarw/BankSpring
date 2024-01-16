package com.sapient.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@ToString
//@Builder
public class JwtResponse {

    private String jwtToken;
    private String userName;

    private JwtResponse() {
        // private constructor to enforce the use of the builder
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public String getUserName() {
        return userName;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private JwtResponse jwtResponse;

        private Builder() {
            jwtResponse = new JwtResponse();
        }

        public Builder jwtToken(String jwtToken) {
            jwtResponse.jwtToken = jwtToken;
            return this;
        }

        public Builder userName(String userName) {
            jwtResponse.userName = userName;
            return this;
        }

        public JwtResponse build() {
            return jwtResponse;
        }
    }
}
