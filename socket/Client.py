import socket
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect(("127.0.0.1", 5010))
while True:
    string = input("->")
    s.send(bytes(string.encode()))
    msg = s.recv(1024)
    print(msg.decode("utf-8"),"recieved from server")