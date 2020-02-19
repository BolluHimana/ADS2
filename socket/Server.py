import socket

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind(("127.0.0.1", 5010))
s.listen(5)

while True:

    clientsocket, address = s.accept()
    print(f"Connection from {address} has been established.")
    msg = clientsocket.recv(1024)
    print(msg.decode("utf-8"),"recieved from client")
    clientsocket.send(bytes(msg))