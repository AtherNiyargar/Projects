import socket
import sys
import os
import threading
import time

def receiving_msg(client_socket):
    while True:
        try:
            print(client_socket.recv(1024).decode())
        except OSError:
            break

def sending_msg(client_socket):
    while True:
        sending_str = input()
        if sending_str == 'Exit':
            client_socket.send(sending_str.encode())
            client_socket.close()
            break
        else:
            client_socket.send(sending_str.encode())

# Start
client_socket = socket.socket()

port = # The server's port'
ip_addr = # The server's ip address'
try:
    client_socket.connect((ip_addr, port))
except ConnectionRefusedError:
    print("The server is either offline, or you are out of range of that server")
    sys.exit()

os.system("clear")
print("Server Connected!\nSend 'Exit' to close the session")

sending_msg_thread = threading.Thread(target=sending_msg, args=(client_socket,))
receiving_msg_thread = threading.Thread(target=receiving_msg, args=(client_socket,))
sending_msg_thread.start()
receiving_msg_thread.start()
