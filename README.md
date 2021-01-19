### Kollus VOD VUE + Springbooot

## 빌드 환경
- Java JDK8
- SpringBoot 2.4.1
- Vue-Cli 2.6.11
- node 14.15.3
- npm 6.14.9

## 빌드전 환경설정

1. Constant 클래스의 연동 키값 입력
``` java
package com.kollus.demo.config;

public class KollusConstant {

    public static final String SECRET_KEY = "";
    public static final String CUSTOM_KEY = "";
    public static final String API_ACCESS_TOKEN = "";
}


```


2. build.gradle 설정
```groovy
project.ext {
    jarBaseName = 'app'
    jarVersion = '0.0.1-' + new Date().format('yyyyMMdd_HHmmss')
}
빌드에서 생성될 jar 파일명을 지정

deploy task 이용시 ssh를 이용하여 jar 파일을 복사 및 실행가능
//원격 서버 정보 입력
remotes {
    webServer {
        host = ''
        user = ''
        password = ''
        port = 0
        knownHosts = allowAnyHosts
    }
}


task deploy {
    doLast {
        ssh.run {
            session(remotes.webServer) {
                final jarName = project.jarBaseName + "-" + project.jarVersion + ".jar"
                put from: "복사할파일 명", into: '타겟디렉토리'
                execute "ps -ef | grep app- | grep -v grep | awk '{print \"kill -9\",\$2}' | sh -v"
                execute "nohup java -jar 실행할 jar 파일명 < /dev/null > /dev/null  2> /dev/null &"
            }
        }
    }
}
Vuejs 빌드 파일은 spring의 resource 폴더로 복사됨
```

2. 로그인 계정
   PrepareUserData class 에서 계정 생성
   
```java
        User admin = new User();
        admin.setUsername("admin");
        admin.setEmail("admin@kollus.com");
        admin.setPassword(passwordEncoder.encode("admin1"));
        Set<Role> admin_roles = new HashSet<>();
        admin_roles.add(role_admin);
        admin.setRoles(admin_roles);
        userRepository.saveAndFlush(admin);
```

