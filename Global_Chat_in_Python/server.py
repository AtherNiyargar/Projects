import socket
import threading

def receiving_msg(client_socket):
    while True:
        received_str = client_socket.recv(1024).decode()
        if received_str == 'Exit':
            client_socket.close()
            clients.remove(client_socket)
            break
        else:
            for c in clients:
                if(c != client_socket):
                    try:
                        c.send(received_str.encode())
                    except: # I don't know how to solve this error!
                        pass

# Start
s = socket.socket()		 
print ("Socket successfully created")

port = #Enter porn number here

s.bind(('', port))		 
print ("socket binded to %s" %(port)) 

s.listen()	 
print ("socket is listening")

clients = []

while True:
    client_socket, addr = s.accept()
    clients.append(client_socket)
    receiving_msg_threaad = threading.Thread(target=receiving_msg, args=(client_socket,))
    receiving_msg_threaad.start()
    print('Connected to client : ', addr)
