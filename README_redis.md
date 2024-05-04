- redis
    - download/install
        - windows
            - https://redis.io/docs/getting-started/installation/install-redis-on-windows/
                - win10+
                    - install wsl
                        - https://docs.microsoft.com/en-us/windows/wsl/install
                            - powershell
                                - wsl --list --online
                                - wsl --install -d Ubuntu
            - old redis releases
                - https://github.com/microsoftarchive/redis/releases
                    - newer: 3.0.54, Redis-x64-3.0.504.msi/zip
    - start/stop method1
        - start
            - redis-server
            - redis-server --port 7777
            - redis-server --help
        - stop
            - lsof -nP -iTCP -sTCP:LISTEN | grep 6379
            - kill PID
                - PID: lsof 命令输出的第 2 列数字
    - start/stop method2
        - brew services start redis
        - brew services stop redis
        - start
            - redis-server
            - redis-server --port 7777
            - redis-server --help
        - stop
            - lsof -nP -iTCP -sTCP:LISTEN | grep 6379
            - kill PID
                - PID: lsof 命令输出的第 2 列数字
    - client
        - redis-cli
            - cmds
                keys *USER
                keys *
                type USER
                flushall
        - redis-cli MONITOR
        

