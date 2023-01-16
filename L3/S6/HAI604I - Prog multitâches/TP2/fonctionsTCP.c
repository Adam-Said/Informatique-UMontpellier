#include <netinet/in.h>
#include <stdio.h> 
#include <arpa/inet.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>

int sendTCP(int sock, void* msg, int sizeMsg) {
    int remaining = sizeMsg;

    while (remaining > 0) {
        printf("Send remaining : %i\n", remaining);
        int res = send(sock, msg + sizeMsg - remaining, remaining, 0);
        if (res <= 0) {
            return res;
        }
        remaining -= res;
    }

    return 1;
}

int recvTCP(int sock, void* msg, int sizeMsg) {
    int remaining = sizeMsg;

    while (remaining > 0) {
        printf("Receive remaining : %i\n", remaining);
        int res = recv(sock, msg + sizeMsg - remaining, remaining, 0);
        if (res <= 0) {
            return res;
        }
        remaining -= res;
    }

    return 1;
}