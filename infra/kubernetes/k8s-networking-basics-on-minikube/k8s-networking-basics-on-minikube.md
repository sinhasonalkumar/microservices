minikube start --nodes 3

➜ mk ip
192.168.59.130

➜ mk addons enable metallb

➜ mk addons configure metallb
-- Enter Load Balancer Start IP: 192.168.59.131
-- Enter Load Balancer End IP: 192.168.59.150
    ▪ Using image metallb/controller:v0.9.6
    ▪ Using image metallb/speaker:v0.9.6
✅  metallb was successfully configured


➜ k create deployment nginx --image=nginx

➜ k scale deploy/nginx --replicas=6

➜ k expose deployments nginx --type=LoadBalancer --target-port=80 --port=80



➜ k get deploy,svc
NAME                    READY   UP-TO-DATE   AVAILABLE   AGE
deployment.apps/nginx   6/6     6            6           4h24m

NAME                 TYPE           CLUSTER-IP       EXTERNAL-IP      PORT(S)        AGE
service/kubernetes   ClusterIP      10.96.0.1        <none>           443/TCP        4h43m
service/nginx        LoadBalancer   10.103.173.168   192.168.59.131   80:30121/TCP   133m


 ➜ k get nodes -o wide
NAME           STATUS   ROLES           AGE     VERSION   INTERNAL-IP      EXTERNAL-IP   OS-IMAGE               KERNEL-VERSION   CONTAINER-RUNTIME
minikube       Ready    control-plane   4h43m   v1.24.1   192.168.59.130   <none>        Buildroot 2021.02.12   5.10.57          docker://20.10.16
minikube-m02   Ready    <none>          4h42m   v1.24.1   192.168.59.131   <none>        Buildroot 2021.02.12   5.10.57          docker://20.10.16
minikube-m03   Ready    <none>          4h41m   v1.24.1   192.168.59.132   <none>        Buildroot 2021.02.12   5.10.57          docker://20.10.16


 ➜ k get pods -o wide
NAME                    READY   STATUS    RESTARTS   AGE     IP           NODE           NOMINATED NODE   READINESS GATES
nginx-8f458dc5b-7n2lg   1/1     Running   0          4h24m   10.244.1.3   minikube-m02   <none>           <none>
nginx-8f458dc5b-bd7g5   1/1     Running   0          4h24m   10.244.0.3   minikube       <none>           <none>
nginx-8f458dc5b-mwhnz   1/1     Running   0          4h24m   10.244.1.2   minikube-m02   <none>           <none>
nginx-8f458dc5b-n62b9   1/1     Running   0          4h24m   10.244.1.4   minikube-m02   <none>           <none>
nginx-8f458dc5b-vvj8n   1/1     Running   0          4h24m   10.244.2.3   minikube-m03   <none>           <none>
nginx-8f458dc5b-wt6jc   1/1     Running   0          4h24m   10.244.2.2   minikube-m03   <none>           <none>


 ➜ k debug -it node/minikube-m02 --image=nicolaka/netshoot

  minikube-m02  ~  route -n

Kernel IP routing table
Destination     Gateway         Genmask         Flags Metric Ref    Use Iface
0.0.0.0         10.0.2.2        0.0.0.0         UG    1024   0        0 eth0
10.0.2.0        0.0.0.0         255.255.255.0   U     0      0        0 eth0
10.0.2.2        0.0.0.0         255.255.255.255 UH    1024   0        0 eth0
10.244.0.0      192.168.59.130  255.255.255.0   UG    0      0        0 eth1
10.244.1.2      0.0.0.0         255.255.255.255 UH    0      0        0 vethec99a3df
10.244.1.3      0.0.0.0         255.255.255.255 UH    0      0        0 vethcd373ea3
10.244.1.4      0.0.0.0         255.255.255.255 UH    0      0        0 veth491078d5
10.244.2.0      192.168.59.132  255.255.255.0   UG    0      0        0 eth1
172.17.0.0      0.0.0.0         255.255.0.0     U     0      0        0 docker0
192.168.59.0    0.0.0.0         255.255.255.0   U     0      0        0 eth1


 minikube-m02  ~  ip addr

1: lo: <LOOPBACK,UP,LOWER_UP> mtu 65536 qdisc noqueue state UNKNOWN group default qlen 1000
    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
    inet 127.0.0.1/8 scope host lo
       valid_lft forever preferred_lft forever
2: eth0: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc pfifo_fast state UP group default qlen 1000
    link/ether 08:00:27:70:44:89 brd ff:ff:ff:ff:ff:ff
    inet 10.0.2.15/24 brd 10.0.2.255 scope global dynamic eth0
       valid_lft 69299sec preferred_lft 69299sec
3: eth1: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc pfifo_fast state UP group default qlen 1000
    link/ether 08:00:27:8b:5f:a4 brd ff:ff:ff:ff:ff:ff
    inet 192.168.59.131/24 brd 192.168.59.255 scope global dynamic eth1
       valid_lft 581sec preferred_lft 581sec
4: sit0@NONE: <NOARP> mtu 1480 qdisc noop state DOWN group default qlen 1000
    link/sit 0.0.0.0 brd 0.0.0.0
5: docker0: <NO-CARRIER,BROADCAST,MULTICAST,UP> mtu 1500 qdisc noqueue state DOWN group default
    link/ether 02:42:1f:05:20:40 brd ff:ff:ff:ff:ff:ff
    inet 172.17.0.1/16 brd 172.17.255.255 scope global docker0
       valid_lft forever preferred_lft forever
6: vethec99a3df@if4: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc noqueue state UP group default
    link/ether 02:02:2e:ab:6c:27 brd ff:ff:ff:ff:ff:ff link-netnsid 0
    inet 10.244.1.1/32 brd 10.244.1.1 scope global vethec99a3df
       valid_lft forever preferred_lft forever
7: vethcd373ea3@if4: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc noqueue state UP group default
    link/ether c6:73:57:9e:3f:7d brd ff:ff:ff:ff:ff:ff link-netnsid 1
    inet 10.244.1.1/32 brd 10.244.1.1 scope global vethcd373ea3
       valid_lft forever preferred_lft forever
8: veth491078d5@if4: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc noqueue state UP group default
    link/ether 56:a9:ce:38:7c:62 brd ff:ff:ff:ff:ff:ff link-netnsid 2
    inet 10.244.1.1/32 brd 10.244.1.1 scope global veth491078d5
       valid_lft forever preferred_lft forever



 ➜ k debug -it nginx-8f458dc5b-mwhnz --image=nicolaka/netshoot

  nginx-8f458dc5b-mwhnz  ~  route -n
Kernel IP routing table
Destination     Gateway         Genmask         Flags Metric Ref    Use Iface
0.0.0.0         10.244.1.1      0.0.0.0         UG    0      0        0 eth0
10.244.1.0      10.244.1.1      255.255.255.0   UG    0      0        0 eth0
10.244.1.1      0.0.0.0         255.255.255.255 UH    0      0        0 eth0


 nginx-8f458dc5b-mwhnz  ~  ip addr
1: lo: <LOOPBACK,UP,LOWER_UP> mtu 65536 qdisc noqueue state UNKNOWN group default qlen 1000
    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
    inet 127.0.0.1/8 scope host lo
       valid_lft forever preferred_lft forever
2: sit0@NONE: <NOARP> mtu 1480 qdisc noop state DOWN group default qlen 1000
    link/sit 0.0.0.0 brd 0.0.0.0
4: eth0@if6: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc noqueue state UP group default
    link/ether 4a:0b:24:f0:d7:aa brd ff:ff:ff:ff:ff:ff link-netnsid 0
    inet 10.244.1.2/24 brd 10.244.1.255 scope global eth0
       valid_lft forever preferred_lft forever




k debug -it nginx-8f458dc5b-n62b9 --image=nicolaka/netshoot

 nginx-8f458dc5b-n62b9  ~  route -n

Kernel IP routing table
Destination     Gateway         Genmask         Flags Metric Ref    Use Iface
0.0.0.0         10.244.1.1      0.0.0.0         UG    0      0        0 eth0
10.244.1.0      10.244.1.1      255.255.255.0   UG    0      0        0 eth0
10.244.1.1      0.0.0.0         255.255.255.255 UH    0      0        0 eth0



nginx-8f458dc5b-n62b9  ~  ip addr
1: lo: <LOOPBACK,UP,LOWER_UP> mtu 65536 qdisc noqueue state UNKNOWN group default qlen 1000
    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
    inet 127.0.0.1/8 scope host lo
       valid_lft forever preferred_lft forever
2: sit0@NONE: <NOARP> mtu 1480 qdisc noop state DOWN group default qlen 1000
    link/sit 0.0.0.0 brd 0.0.0.0
4: eth0@if8: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc noqueue state UP group default
    link/ether 16:44:3a:5f:91:c9 brd ff:ff:ff:ff:ff:ff link-netnsid 0
    inet 10.244.1.4/24 brd 10.244.1.255 scope global eth0
       valid_lft forever preferred_lft forever



 ➜ k debug -it nginx-8f458dc5b-7n2lg --image=nicolaka/netshoot

nginx-8f458dc5b-7n2lg  ~  route -n

Kernel IP routing table
Destination     Gateway         Genmask         Flags Metric Ref    Use Iface
0.0.0.0         10.244.1.1      0.0.0.0         UG    0      0        0 eth0
10.244.1.0      10.244.1.1      255.255.255.0   UG    0      0        0 eth0
10.244.1.1      0.0.0.0         255.255.255.255 UH    0      0        0 eth0


nginx-8f458dc5b-7n2lg  ~  ip addr

1: lo: <LOOPBACK,UP,LOWER_UP> mtu 65536 qdisc noqueue state UNKNOWN group default qlen 1000
    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
    inet 127.0.0.1/8 scope host lo
       valid_lft forever preferred_lft forever
2: sit0@NONE: <NOARP> mtu 1480 qdisc noop state DOWN group default qlen 1000
    link/sit 0.0.0.0 brd 0.0.0.0
4: eth0@if7: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc noqueue state UP group default
    link/ether 3a:81:00:c1:ff:8f brd ff:ff:ff:ff:ff:ff link-netnsid 0
    inet 10.244.1.3/24 brd 10.244.1.255 scope global eth0
       valid_lft forever preferred_lft forever

---

k debug -it node/minikube-m03 --image=nicolaka/netshoot

 minikube-m03  ~  route -n
Kernel IP routing table
Destination     Gateway         Genmask         Flags Metric Ref    Use Iface
0.0.0.0         10.0.2.2        0.0.0.0         UG    1024   0        0 eth0
10.0.2.0        0.0.0.0         255.255.255.0   U     0      0        0 eth0
10.0.2.2        0.0.0.0         255.255.255.255 UH    1024   0        0 eth0
10.244.0.0      192.168.59.130  255.255.255.0   UG    0      0        0 eth1
10.244.1.0      192.168.59.131  255.255.255.0   UG    0      0        0 eth1
10.244.2.2      0.0.0.0         255.255.255.255 UH    0      0        0 vetha2a6d4ce
10.244.2.3      0.0.0.0         255.255.255.255 UH    0      0        0 veth006e5e3e
10.244.2.4      0.0.0.0         255.255.255.255 UH    0      0        0 vethc2025297
172.17.0.0      0.0.0.0         255.255.0.0     U     0      0        0 docker0
192.168.59.0    0.0.0.0         255.255.255.0   U     0      0        0 eth1


 minikube-m03  ~  ip addr

1: lo: <LOOPBACK,UP,LOWER_UP> mtu 65536 qdisc noqueue state UNKNOWN group default qlen 1000
    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
    inet 127.0.0.1/8 scope host lo
       valid_lft forever preferred_lft forever
2: eth0: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc pfifo_fast state UP group default qlen 1000
    link/ether 08:00:27:6c:3f:f9 brd ff:ff:ff:ff:ff:ff
    inet 10.0.2.15/24 brd 10.0.2.255 scope global dynamic eth0
       valid_lft 68872sec preferred_lft 68872sec
3: eth1: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc pfifo_fast state UP group default qlen 1000
    link/ether 08:00:27:a1:09:71 brd ff:ff:ff:ff:ff:ff
    inet 192.168.59.132/24 brd 192.168.59.255 scope global dynamic eth1
       valid_lft 448sec preferred_lft 448sec
4: sit0@NONE: <NOARP> mtu 1480 qdisc noop state DOWN group default qlen 1000
    link/sit 0.0.0.0 brd 0.0.0.0
5: docker0: <NO-CARRIER,BROADCAST,MULTICAST,UP> mtu 1500 qdisc noqueue state DOWN group default
    link/ether 02:42:ee:c4:bd:0c brd ff:ff:ff:ff:ff:ff
    inet 172.17.0.1/16 brd 172.17.255.255 scope global docker0
       valid_lft forever preferred_lft forever
6: vetha2a6d4ce@if4: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc noqueue state UP group default
    link/ether 66:f1:a5:7b:88:4e brd ff:ff:ff:ff:ff:ff link-netnsid 0
    inet 10.244.2.1/32 brd 10.244.2.1 scope global vetha2a6d4ce
       valid_lft forever preferred_lft forever
7: veth006e5e3e@if4: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc noqueue state UP group default
    link/ether be:66:a2:d2:fb:dd brd ff:ff:ff:ff:ff:ff link-netnsid 1
    inet 10.244.2.1/32 brd 10.244.2.1 scope global veth006e5e3e
       valid_lft forever preferred_lft forever
8: vethc2025297@if4: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc noqueue state UP group default
    link/ether 76:42:52:d5:63:75 brd ff:ff:ff:ff:ff:ff link-netnsid 2
    inet 10.244.2.1/32 brd 10.244.2.1 scope global vethc2025297
       valid_lft forever preferred_lft forever


k debug -it nginx-8f458dc5b-wt6jc --image=nicolaka/netshoot


 nginx-8f458dc5b-wt6jc  ~  route -n

Kernel IP routing table
Destination     Gateway         Genmask         Flags Metric Ref    Use Iface
0.0.0.0         10.244.2.1      0.0.0.0         UG    0      0        0 eth0
10.244.2.0      10.244.2.1      255.255.255.0   UG    0      0        0 eth0
10.244.2.1      0.0.0.0         255.255.255.255 UH    0      0        0 eth0


 nginx-8f458dc5b-wt6jc  ~  ip addr

1: lo: <LOOPBACK,UP,LOWER_UP> mtu 65536 qdisc noqueue state UNKNOWN group default qlen 1000
    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
    inet 127.0.0.1/8 scope host lo
       valid_lft forever preferred_lft forever
2: sit0@NONE: <NOARP> mtu 1480 qdisc noop state DOWN group default qlen 1000
    link/sit 0.0.0.0 brd 0.0.0.0
4: eth0@if6: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc noqueue state UP group default
    link/ether 86:71:6a:a6:2c:4d brd ff:ff:ff:ff:ff:ff link-netnsid 0
    inet 10.244.2.2/24 brd 10.244.2.255 scope global eth0
       valid_lft forever preferred_lft forever



k debug -it nginx-8f458dc5b-vvj8n --image=nicolaka/netshoot

 nginx-8f458dc5b-vvj8n  ~  route -n

Kernel IP routing table
Destination     Gateway         Genmask         Flags Metric Ref    Use Iface
0.0.0.0         10.244.2.1      0.0.0.0         UG    0      0        0 eth0
10.244.2.0      10.244.2.1      255.255.255.0   UG    0      0        0 eth0
10.244.2.1      0.0.0.0         255.255.255.255 UH    0      0        0 eth0


 nginx-8f458dc5b-vvj8n  ~  ip addr
1: lo: <LOOPBACK,UP,LOWER_UP> mtu 65536 qdisc noqueue state UNKNOWN group default qlen 1000
    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
    inet 127.0.0.1/8 scope host lo
       valid_lft forever preferred_lft forever
2: sit0@NONE: <NOARP> mtu 1480 qdisc noop state DOWN group default qlen 1000
    link/sit 0.0.0.0 brd 0.0.0.0
4: eth0@if7: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc noqueue state UP group default
    link/ether da:bc:d4:4d:92:53 brd ff:ff:ff:ff:ff:ff link-netnsid 0
    inet 10.244.2.3/24 brd 10.244.2.255 scope global eth0
       valid_lft forever preferred_lft forever

---

k debug -it node/minikube --image=nicolaka/netshoot

 minikube  ~  route -n
Kernel IP routing table
Destination     Gateway         Genmask         Flags Metric Ref    Use Iface
0.0.0.0         10.0.2.2        0.0.0.0         UG    1024   0        0 eth0
10.0.2.0        0.0.0.0         255.255.255.0   U     0      0        0 eth0
10.0.2.2        0.0.0.0         255.255.255.255 UH    1024   0        0 eth0
10.244.0.2      0.0.0.0         255.255.255.255 UH    0      0        0 veth662f43b4
10.244.0.3      0.0.0.0         255.255.255.255 UH    0      0        0 vetheae823f5
10.244.1.0      192.168.59.131  255.255.255.0   UG    0      0        0 eth1
10.244.2.0      192.168.59.132  255.255.255.0   UG    0      0        0 eth1
172.17.0.0      0.0.0.0         255.255.0.0     U     0      0        0 docker0
192.168.59.0    0.0.0.0         255.255.255.0   U     0      0        0 eth1


 minikube  ~  ip addr

1: lo: <LOOPBACK,UP,LOWER_UP> mtu 65536 qdisc noqueue state UNKNOWN group default qlen 1000
    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
    inet 127.0.0.1/8 scope host lo
       valid_lft forever preferred_lft forever
2: eth0: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc pfifo_fast state UP group default qlen 1000
    link/ether 08:00:27:28:ca:3b brd ff:ff:ff:ff:ff:ff
    inet 10.0.2.15/24 brd 10.0.2.255 scope global dynamic eth0
       valid_lft 68406sec preferred_lft 68406sec
3: eth1: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc pfifo_fast state UP group default qlen 1000
    link/ether 08:00:27:bd:a0:24 brd ff:ff:ff:ff:ff:ff
    inet 192.168.59.130/24 brd 192.168.59.255 scope global dynamic eth1
       valid_lft 583sec preferred_lft 583sec
4: sit0@NONE: <NOARP> mtu 1480 qdisc noop state DOWN group default qlen 1000
    link/sit 0.0.0.0 brd 0.0.0.0
5: docker0: <NO-CARRIER,BROADCAST,MULTICAST,UP> mtu 1500 qdisc noqueue state DOWN group default
    link/ether 02:42:bb:b2:57:c8 brd ff:ff:ff:ff:ff:ff
    inet 172.17.0.1/16 brd 172.17.255.255 scope global docker0
       valid_lft forever preferred_lft forever
6: veth662f43b4@if4: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc noqueue state UP group default
    link/ether 1e:28:27:7a:50:42 brd ff:ff:ff:ff:ff:ff link-netnsid 0
    inet 10.244.0.1/32 brd 10.244.0.1 scope global veth662f43b4
       valid_lft forever preferred_lft forever
7: vetheae823f5@if4: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc noqueue state UP group default
    link/ether 9a:12:03:9b:43:4c brd ff:ff:ff:ff:ff:ff link-netnsid 1
    inet 10.244.0.1/32 brd 10.244.0.1 scope global vetheae823f5
       valid_lft forever preferred_lft forever



 k debug -it nginx-8f458dc5b-bd7g5 --image=nicolaka/netshoot

 nginx-8f458dc5b-bd7g5  ~  route -n

Kernel IP routing table
Destination     Gateway         Genmask         Flags Metric Ref    Use Iface
0.0.0.0         10.244.0.1      0.0.0.0         UG    0      0        0 eth0
10.244.0.0      10.244.0.1      255.255.255.0   UG    0      0        0 eth0
10.244.0.1      0.0.0.0         255.255.255.255 UH    0      0        0 eth0 


 nginx-8f458dc5b-bd7g5  ~  ip addr
1: lo: <LOOPBACK,UP,LOWER_UP> mtu 65536 qdisc noqueue state UNKNOWN group default qlen 1000
    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
    inet 127.0.0.1/8 scope host lo
       valid_lft forever preferred_lft forever
2: sit0@NONE: <NOARP> mtu 1480 qdisc noop state DOWN group default qlen 1000
    link/sit 0.0.0.0 brd 0.0.0.0
4: eth0@if7: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc noqueue state UP group default
    link/ether fa:45:5b:0f:9d:55 brd ff:ff:ff:ff:ff:ff link-netnsid 0
    inet 10.244.0.3/24 brd 10.244.0.255 scope global eth0
       valid_lft forever preferred_lft forever


----

➜ mk ssh -n minikube-m02

$ sudo iptables -t nat -L -n
Chain PREROUTING (policy ACCEPT)
target     prot opt source               destination
KUBE-SERVICES  all  --  0.0.0.0/0            0.0.0.0/0            /* kubernetes service portals */
DOCKER     all  --  0.0.0.0/0            0.0.0.0/0            ADDRTYPE match dst-type LOCAL

Chain INPUT (policy ACCEPT)
target     prot opt source               destination

Chain OUTPUT (policy ACCEPT)
target     prot opt source               destination
KUBE-SERVICES  all  --  0.0.0.0/0            0.0.0.0/0            /* kubernetes service portals */
DOCKER     all  --  0.0.0.0/0           !127.0.0.0/8          ADDRTYPE match dst-type LOCAL

Chain POSTROUTING (policy ACCEPT)
target     prot opt source               destination
KUBE-POSTROUTING  all  --  0.0.0.0/0            0.0.0.0/0            /* kubernetes postrouting rules */
MASQUERADE  all  --  172.17.0.0/16        0.0.0.0/0
KIND-MASQ-AGENT  all  --  0.0.0.0/0            0.0.0.0/0            ADDRTYPE match dst-type !LOCAL /* kind-masq-agent: ensure nat POSTROUTING directs all non-LOCAL destination traffic to our custom KIND-MASQ-AGENT chain */

Chain DOCKER (2 references)
target     prot opt source               destination
RETURN     all  --  0.0.0.0/0            0.0.0.0/0

Chain KIND-MASQ-AGENT (1 references)
target     prot opt source               destination
RETURN     all  --  0.0.0.0/0            10.244.0.0/16        /* kind-masq-agent: local traffic is not subject to MASQUERADE */
MASQUERADE  all  --  0.0.0.0/0            0.0.0.0/0            /* kind-masq-agent: outbound traffic is subject to MASQUERADE (must be last in chain) */

Chain KUBE-EXT-2CMXP7HKUVJN7L6M (2 references)
target     prot opt source               destination
KUBE-MARK-MASQ  all  --  0.0.0.0/0            0.0.0.0/0            /* masquerade traffic for default/nginx external destinations */
KUBE-SVC-2CMXP7HKUVJN7L6M  all  --  0.0.0.0/0            0.0.0.0/0

Chain KUBE-KUBELET-CANARY (0 references)
target     prot opt source               destination

Chain KUBE-MARK-DROP (0 references)
target     prot opt source               destination
MARK       all  --  0.0.0.0/0            0.0.0.0/0            MARK or 0x8000

Chain KUBE-MARK-MASQ (16 references)
target     prot opt source               destination
MARK       all  --  0.0.0.0/0            0.0.0.0/0            MARK or 0x4000

Chain KUBE-NODEPORTS (1 references)
target     prot opt source               destination
KUBE-EXT-2CMXP7HKUVJN7L6M  tcp  --  0.0.0.0/0            0.0.0.0/0            /* default/nginx */ tcp dpt:30121

Chain KUBE-POSTROUTING (1 references)
target     prot opt source               destination
RETURN     all  --  0.0.0.0/0            0.0.0.0/0            mark match ! 0x4000/0x4000
MARK       all  --  0.0.0.0/0            0.0.0.0/0            MARK xor 0x4000
MASQUERADE  all  --  0.0.0.0/0            0.0.0.0/0            /* kubernetes service traffic requiring SNAT */ random-fully

Chain KUBE-PROXY-CANARY (0 references)
target     prot opt source               destination

Chain KUBE-SEP-GA5F2M4O5E56ZCK4 (1 references)
target     prot opt source               destination
KUBE-MARK-MASQ  all  --  10.244.0.3           0.0.0.0/0            /* default/nginx */
DNAT       tcp  --  0.0.0.0/0            0.0.0.0/0            /* default/nginx */ tcp to:10.244.0.3:80

Chain KUBE-SEP-HUXRPHBUO34UVPEF (1 references)
target     prot opt source               destination
KUBE-MARK-MASQ  all  --  10.244.2.2           0.0.0.0/0            /* default/nginx */
DNAT       tcp  --  0.0.0.0/0            0.0.0.0/0            /* default/nginx */ tcp to:10.244.2.2:80

Chain KUBE-SEP-I7J7B2HF6PITCMSA (1 references)
target     prot opt source               destination
KUBE-MARK-MASQ  all  --  10.244.1.3           0.0.0.0/0            /* default/nginx */
DNAT       tcp  --  0.0.0.0/0            0.0.0.0/0            /* default/nginx */ tcp to:10.244.1.3:80

Chain KUBE-SEP-IT2ZTR26TO4XFPTO (1 references)
target     prot opt source               destination
KUBE-MARK-MASQ  all  --  10.244.0.2           0.0.0.0/0            /* kube-system/kube-dns:dns-tcp */
DNAT       tcp  --  0.0.0.0/0            0.0.0.0/0            /* kube-system/kube-dns:dns-tcp */ tcp to:10.244.0.2:53

Chain KUBE-SEP-L3TEFOPRMLPWJIHV (1 references)
target     prot opt source               destination
KUBE-MARK-MASQ  all  --  10.244.1.2           0.0.0.0/0            /* default/nginx */
DNAT       tcp  --  0.0.0.0/0            0.0.0.0/0            /* default/nginx */ tcp to:10.244.1.2:80

Chain KUBE-SEP-N4G2XR5TDX7PQE7P (1 references)
target     prot opt source               destination
KUBE-MARK-MASQ  all  --  10.244.0.2           0.0.0.0/0            /* kube-system/kube-dns:metrics */
DNAT       tcp  --  0.0.0.0/0            0.0.0.0/0            /* kube-system/kube-dns:metrics */ tcp to:10.244.0.2:9153

Chain KUBE-SEP-OI7NWIH7RAT2AOGF (1 references)
target     prot opt source               destination
KUBE-MARK-MASQ  all  --  10.244.2.3           0.0.0.0/0            /* default/nginx */
DNAT       tcp  --  0.0.0.0/0            0.0.0.0/0            /* default/nginx */ tcp to:10.244.2.3:80

Chain KUBE-SEP-P33L4LSPB7KVLSK6 (1 references)
target     prot opt source               destination
KUBE-MARK-MASQ  all  --  10.244.1.4           0.0.0.0/0            /* default/nginx */
DNAT       tcp  --  0.0.0.0/0            0.0.0.0/0            /* default/nginx */ tcp to:10.244.1.4:80

Chain KUBE-SEP-TAB3TNQU36FOVLKX (1 references)
target     prot opt source               destination
KUBE-MARK-MASQ  all  --  192.168.59.130       0.0.0.0/0            /* default/kubernetes:https */
DNAT       tcp  --  0.0.0.0/0            0.0.0.0/0            /* default/kubernetes:https */ tcp to:192.168.59.130:8443

Chain KUBE-SEP-YIL6JZP7A3QYXJU2 (1 references)
target     prot opt source               destination
KUBE-MARK-MASQ  all  --  10.244.0.2           0.0.0.0/0            /* kube-system/kube-dns:dns */
DNAT       udp  --  0.0.0.0/0            0.0.0.0/0            /* kube-system/kube-dns:dns */ udp to:10.244.0.2:53

Chain KUBE-SERVICES (2 references)
target     prot opt source               destination
KUBE-SVC-TCOU7JCQXEZGVUNU  udp  --  0.0.0.0/0            10.96.0.10           /* kube-system/kube-dns:dns cluster IP */ udp dpt:53
KUBE-SVC-ERIFXISQEP7F7OF4  tcp  --  0.0.0.0/0            10.96.0.10           /* kube-system/kube-dns:dns-tcp cluster IP */ tcp dpt:53
KUBE-SVC-JD5MR3NA4I4DYORP  tcp  --  0.0.0.0/0            10.96.0.10           /* kube-system/kube-dns:metrics cluster IP */ tcp dpt:9153
KUBE-SVC-NPX46M4PTMTKRN6Y  tcp  --  0.0.0.0/0            10.96.0.1            /* default/kubernetes:https cluster IP */ tcp dpt:443
KUBE-SVC-2CMXP7HKUVJN7L6M  tcp  --  0.0.0.0/0            10.103.173.168       /* default/nginx cluster IP */ tcp dpt:80
KUBE-EXT-2CMXP7HKUVJN7L6M  tcp  --  0.0.0.0/0            192.168.59.131       /* default/nginx loadbalancer IP */ tcp dpt:80
KUBE-NODEPORTS  all  --  0.0.0.0/0            0.0.0.0/0            /* kubernetes service nodeports; NOTE: this must be the last rule in this chain */ ADDRTYPE match dst-type LOCAL

Chain KUBE-SVC-2CMXP7HKUVJN7L6M (2 references)
target     prot opt source               destination
KUBE-MARK-MASQ  tcp  -- !10.244.0.0/16        10.103.173.168       /* default/nginx cluster IP */ tcp dpt:80
KUBE-SEP-GA5F2M4O5E56ZCK4  all  --  0.0.0.0/0            0.0.0.0/0            /* default/nginx -> 10.244.0.3:80 */ statistic mode random probability 0.16666666651
KUBE-SEP-L3TEFOPRMLPWJIHV  all  --  0.0.0.0/0            0.0.0.0/0            /* default/nginx -> 10.244.1.2:80 */ statistic mode random probability 0.20000000019
KUBE-SEP-I7J7B2HF6PITCMSA  all  --  0.0.0.0/0            0.0.0.0/0            /* default/nginx -> 10.244.1.3:80 */ statistic mode random probability 0.25000000000
KUBE-SEP-P33L4LSPB7KVLSK6  all  --  0.0.0.0/0            0.0.0.0/0            /* default/nginx -> 10.244.1.4:80 */ statistic mode random probability 0.33333333349
KUBE-SEP-HUXRPHBUO34UVPEF  all  --  0.0.0.0/0            0.0.0.0/0            /* default/nginx -> 10.244.2.2:80 */ statistic mode random probability 0.50000000000
KUBE-SEP-OI7NWIH7RAT2AOGF  all  --  0.0.0.0/0            0.0.0.0/0            /* default/nginx -> 10.244.2.3:80 */

Chain KUBE-SVC-ERIFXISQEP7F7OF4 (1 references)
target     prot opt source               destination
KUBE-MARK-MASQ  tcp  -- !10.244.0.0/16        10.96.0.10           /* kube-system/kube-dns:dns-tcp cluster IP */ tcp dpt:53
KUBE-SEP-IT2ZTR26TO4XFPTO  all  --  0.0.0.0/0            0.0.0.0/0            /* kube-system/kube-dns:dns-tcp -> 10.244.0.2:53 */

Chain KUBE-SVC-JD5MR3NA4I4DYORP (1 references)
target     prot opt source               destination
KUBE-MARK-MASQ  tcp  -- !10.244.0.0/16        10.96.0.10           /* kube-system/kube-dns:metrics cluster IP */ tcp dpt:9153
KUBE-SEP-N4G2XR5TDX7PQE7P  all  --  0.0.0.0/0            0.0.0.0/0            /* kube-system/kube-dns:metrics -> 10.244.0.2:9153 */

Chain KUBE-SVC-NPX46M4PTMTKRN6Y (1 references)
target     prot opt source               destination
KUBE-MARK-MASQ  tcp  -- !10.244.0.0/16        10.96.0.1            /* default/kubernetes:https cluster IP */ tcp dpt:443
KUBE-SEP-TAB3TNQU36FOVLKX  all  --  0.0.0.0/0            0.0.0.0/0            /* default/kubernetes:https -> 192.168.59.130:8443 */

Chain KUBE-SVC-TCOU7JCQXEZGVUNU (1 references)
target     prot opt source               destination
KUBE-MARK-MASQ  udp  -- !10.244.0.0/16        10.96.0.10           /* kube-system/kube-dns:dns cluster IP */ udp dpt:53
KUBE-SEP-YIL6JZP7A3QYXJU2  all  --  0.0.0.0/0            0.0.0.0/0            /* kube-system/kube-dns:dns -> 10.244.0.2:53 */
$


---

 ➜ mk ssh -n minikube-m03


 $ sudo iptables -t nat -L -n
Chain PREROUTING (policy ACCEPT)
target     prot opt source               destination
KUBE-SERVICES  all  --  0.0.0.0/0            0.0.0.0/0            /* kubernetes service portals */
DOCKER     all  --  0.0.0.0/0            0.0.0.0/0            ADDRTYPE match dst-type LOCAL

Chain INPUT (policy ACCEPT)
target     prot opt source               destination

Chain OUTPUT (policy ACCEPT)
target     prot opt source               destination
KUBE-SERVICES  all  --  0.0.0.0/0            0.0.0.0/0            /* kubernetes service portals */
DOCKER     all  --  0.0.0.0/0           !127.0.0.0/8          ADDRTYPE match dst-type LOCAL

Chain POSTROUTING (policy ACCEPT)
target     prot opt source               destination
KUBE-POSTROUTING  all  --  0.0.0.0/0            0.0.0.0/0            /* kubernetes postrouting rules */
MASQUERADE  all  --  172.17.0.0/16        0.0.0.0/0
KIND-MASQ-AGENT  all  --  0.0.0.0/0            0.0.0.0/0            ADDRTYPE match dst-type !LOCAL /* kind-masq-agent: ensure nat POSTROUTING directs all non-LOCAL destination traffic to our custom KIND-MASQ-AGENT chain */

Chain DOCKER (2 references)
target     prot opt source               destination
RETURN     all  --  0.0.0.0/0            0.0.0.0/0

Chain KIND-MASQ-AGENT (1 references)
target     prot opt source               destination
RETURN     all  --  0.0.0.0/0            10.244.0.0/16        /* kind-masq-agent: local traffic is not subject to MASQUERADE */
MASQUERADE  all  --  0.0.0.0/0            0.0.0.0/0            /* kind-masq-agent: outbound traffic is subject to MASQUERADE (must be last in chain) */

Chain KUBE-EXT-2CMXP7HKUVJN7L6M (2 references)
target     prot opt source               destination
KUBE-MARK-MASQ  all  --  0.0.0.0/0            0.0.0.0/0            /* masquerade traffic for default/nginx external destinations */
KUBE-SVC-2CMXP7HKUVJN7L6M  all  --  0.0.0.0/0            0.0.0.0/0

Chain KUBE-KUBELET-CANARY (0 references)
target     prot opt source               destination

Chain KUBE-MARK-DROP (0 references)
target     prot opt source               destination
MARK       all  --  0.0.0.0/0            0.0.0.0/0            MARK or 0x8000

Chain KUBE-MARK-MASQ (16 references)
target     prot opt source               destination
MARK       all  --  0.0.0.0/0            0.0.0.0/0            MARK or 0x4000

Chain KUBE-NODEPORTS (1 references)
target     prot opt source               destination
KUBE-EXT-2CMXP7HKUVJN7L6M  tcp  --  0.0.0.0/0            0.0.0.0/0            /* default/nginx */ tcp dpt:30121

Chain KUBE-POSTROUTING (1 references)
target     prot opt source               destination
RETURN     all  --  0.0.0.0/0            0.0.0.0/0            mark match ! 0x4000/0x4000
MARK       all  --  0.0.0.0/0            0.0.0.0/0            MARK xor 0x4000
MASQUERADE  all  --  0.0.0.0/0            0.0.0.0/0            /* kubernetes service traffic requiring SNAT */ random-fully

Chain KUBE-PROXY-CANARY (0 references)
target     prot opt source               destination

Chain KUBE-SEP-GA5F2M4O5E56ZCK4 (1 references)
target     prot opt source               destination
KUBE-MARK-MASQ  all  --  10.244.0.3           0.0.0.0/0            /* default/nginx */
DNAT       tcp  --  0.0.0.0/0            0.0.0.0/0            /* default/nginx */ tcp to:10.244.0.3:80

Chain KUBE-SEP-HUXRPHBUO34UVPEF (1 references)
target     prot opt source               destination
KUBE-MARK-MASQ  all  --  10.244.2.2           0.0.0.0/0            /* default/nginx */
DNAT       tcp  --  0.0.0.0/0            0.0.0.0/0            /* default/nginx */ tcp to:10.244.2.2:80

Chain KUBE-SEP-I7J7B2HF6PITCMSA (1 references)
target     prot opt source               destination
KUBE-MARK-MASQ  all  --  10.244.1.3           0.0.0.0/0            /* default/nginx */
DNAT       tcp  --  0.0.0.0/0            0.0.0.0/0            /* default/nginx */ tcp to:10.244.1.3:80

Chain KUBE-SEP-IT2ZTR26TO4XFPTO (1 references)
target     prot opt source               destination
KUBE-MARK-MASQ  all  --  10.244.0.2           0.0.0.0/0            /* kube-system/kube-dns:dns-tcp */
DNAT       tcp  --  0.0.0.0/0            0.0.0.0/0            /* kube-system/kube-dns:dns-tcp */ tcp to:10.244.0.2:53

Chain KUBE-SEP-L3TEFOPRMLPWJIHV (1 references)
target     prot opt source               destination
KUBE-MARK-MASQ  all  --  10.244.1.2           0.0.0.0/0            /* default/nginx */
DNAT       tcp  --  0.0.0.0/0            0.0.0.0/0            /* default/nginx */ tcp to:10.244.1.2:80

Chain KUBE-SEP-N4G2XR5TDX7PQE7P (1 references)
target     prot opt source               destination
KUBE-MARK-MASQ  all  --  10.244.0.2           0.0.0.0/0            /* kube-system/kube-dns:metrics */
DNAT       tcp  --  0.0.0.0/0            0.0.0.0/0            /* kube-system/kube-dns:metrics */ tcp to:10.244.0.2:9153

Chain KUBE-SEP-OI7NWIH7RAT2AOGF (1 references)
target     prot opt source               destination
KUBE-MARK-MASQ  all  --  10.244.2.3           0.0.0.0/0            /* default/nginx */
DNAT       tcp  --  0.0.0.0/0            0.0.0.0/0            /* default/nginx */ tcp to:10.244.2.3:80

Chain KUBE-SEP-P33L4LSPB7KVLSK6 (1 references)
target     prot opt source               destination
KUBE-MARK-MASQ  all  --  10.244.1.4           0.0.0.0/0            /* default/nginx */
DNAT       tcp  --  0.0.0.0/0            0.0.0.0/0            /* default/nginx */ tcp to:10.244.1.4:80

Chain KUBE-SEP-TAB3TNQU36FOVLKX (1 references)
target     prot opt source               destination
KUBE-MARK-MASQ  all  --  192.168.59.130       0.0.0.0/0            /* default/kubernetes:https */
DNAT       tcp  --  0.0.0.0/0            0.0.0.0/0            /* default/kubernetes:https */ tcp to:192.168.59.130:8443

Chain KUBE-SEP-YIL6JZP7A3QYXJU2 (1 references)
target     prot opt source               destination
KUBE-MARK-MASQ  all  --  10.244.0.2           0.0.0.0/0            /* kube-system/kube-dns:dns */
DNAT       udp  --  0.0.0.0/0            0.0.0.0/0            /* kube-system/kube-dns:dns */ udp to:10.244.0.2:53

Chain KUBE-SERVICES (2 references)
target     prot opt source               destination
KUBE-SVC-TCOU7JCQXEZGVUNU  udp  --  0.0.0.0/0            10.96.0.10           /* kube-system/kube-dns:dns cluster IP */ udp dpt:53
KUBE-SVC-ERIFXISQEP7F7OF4  tcp  --  0.0.0.0/0            10.96.0.10           /* kube-system/kube-dns:dns-tcp cluster IP */ tcp dpt:53
KUBE-SVC-JD5MR3NA4I4DYORP  tcp  --  0.0.0.0/0            10.96.0.10           /* kube-system/kube-dns:metrics cluster IP */ tcp dpt:9153
KUBE-SVC-2CMXP7HKUVJN7L6M  tcp  --  0.0.0.0/0            10.103.173.168       /* default/nginx cluster IP */ tcp dpt:80
KUBE-EXT-2CMXP7HKUVJN7L6M  tcp  --  0.0.0.0/0            192.168.59.131       /* default/nginx loadbalancer IP */ tcp dpt:80
KUBE-SVC-NPX46M4PTMTKRN6Y  tcp  --  0.0.0.0/0            10.96.0.1            /* default/kubernetes:https cluster IP */ tcp dpt:443
KUBE-NODEPORTS  all  --  0.0.0.0/0            0.0.0.0/0            /* kubernetes service nodeports; NOTE: this must be the last rule in this chain */ ADDRTYPE match dst-type LOCAL

Chain KUBE-SVC-2CMXP7HKUVJN7L6M (2 references)
target     prot opt source               destination
KUBE-MARK-MASQ  tcp  -- !10.244.0.0/16        10.103.173.168       /* default/nginx cluster IP */ tcp dpt:80
KUBE-SEP-GA5F2M4O5E56ZCK4  all  --  0.0.0.0/0            0.0.0.0/0            /* default/nginx -> 10.244.0.3:80 */ statistic mode random probability 0.16666666651
KUBE-SEP-L3TEFOPRMLPWJIHV  all  --  0.0.0.0/0            0.0.0.0/0            /* default/nginx -> 10.244.1.2:80 */ statistic mode random probability 0.20000000019
KUBE-SEP-I7J7B2HF6PITCMSA  all  --  0.0.0.0/0            0.0.0.0/0            /* default/nginx -> 10.244.1.3:80 */ statistic mode random probability 0.25000000000
KUBE-SEP-P33L4LSPB7KVLSK6  all  --  0.0.0.0/0            0.0.0.0/0            /* default/nginx -> 10.244.1.4:80 */ statistic mode random probability 0.33333333349
KUBE-SEP-HUXRPHBUO34UVPEF  all  --  0.0.0.0/0            0.0.0.0/0            /* default/nginx -> 10.244.2.2:80 */ statistic mode random probability 0.50000000000
KUBE-SEP-OI7NWIH7RAT2AOGF  all  --  0.0.0.0/0            0.0.0.0/0            /* default/nginx -> 10.244.2.3:80 */

Chain KUBE-SVC-ERIFXISQEP7F7OF4 (1 references)
target     prot opt source               destination
KUBE-MARK-MASQ  tcp  -- !10.244.0.0/16        10.96.0.10           /* kube-system/kube-dns:dns-tcp cluster IP */ tcp dpt:53
KUBE-SEP-IT2ZTR26TO4XFPTO  all  --  0.0.0.0/0            0.0.0.0/0            /* kube-system/kube-dns:dns-tcp -> 10.244.0.2:53 */

Chain KUBE-SVC-JD5MR3NA4I4DYORP (1 references)
target     prot opt source               destination
KUBE-MARK-MASQ  tcp  -- !10.244.0.0/16        10.96.0.10           /* kube-system/kube-dns:metrics cluster IP */ tcp dpt:9153
KUBE-SEP-N4G2XR5TDX7PQE7P  all  --  0.0.0.0/0            0.0.0.0/0            /* kube-system/kube-dns:metrics -> 10.244.0.2:9153 */

Chain KUBE-SVC-NPX46M4PTMTKRN6Y (1 references)
target     prot opt source               destination
KUBE-MARK-MASQ  tcp  -- !10.244.0.0/16        10.96.0.1            /* default/kubernetes:https cluster IP */ tcp dpt:443
KUBE-SEP-TAB3TNQU36FOVLKX  all  --  0.0.0.0/0            0.0.0.0/0            /* default/kubernetes:https -> 192.168.59.130:8443 */

Chain KUBE-SVC-TCOU7JCQXEZGVUNU (1 references)
target     prot opt source               destination
KUBE-MARK-MASQ  udp  -- !10.244.0.0/16        10.96.0.10           /* kube-system/kube-dns:dns cluster IP */ udp dpt:53
KUBE-SEP-YIL6JZP7A3QYXJU2  all  --  0.0.0.0/0            0.0.0.0/0            /* kube-system/kube-dns:dns -> 10.244.0.2:53 */
$


---


 ➜ mk ssh -n minikube


 $ sudo iptables -t nat -L -n
Chain PREROUTING (policy ACCEPT)
target     prot opt source               destination
KUBE-SERVICES  all  --  0.0.0.0/0            0.0.0.0/0            /* kubernetes service portals */
DOCKER     all  --  0.0.0.0/0            0.0.0.0/0            ADDRTYPE match dst-type LOCAL

Chain INPUT (policy ACCEPT)
target     prot opt source               destination

Chain OUTPUT (policy ACCEPT)
target     prot opt source               destination
KUBE-SERVICES  all  --  0.0.0.0/0            0.0.0.0/0            /* kubernetes service portals */
DOCKER     all  --  0.0.0.0/0           !127.0.0.0/8          ADDRTYPE match dst-type LOCAL

Chain POSTROUTING (policy ACCEPT)
target     prot opt source               destination
KUBE-POSTROUTING  all  --  0.0.0.0/0            0.0.0.0/0            /* kubernetes postrouting rules */
MASQUERADE  all  --  172.17.0.0/16        0.0.0.0/0
KIND-MASQ-AGENT  all  --  0.0.0.0/0            0.0.0.0/0            ADDRTYPE match dst-type !LOCAL /* kind-masq-agent: ensure nat POSTROUTING directs all non-LOCAL destination traffic to our custom KIND-MASQ-AGENT chain */

Chain DOCKER (2 references)
target     prot opt source               destination
RETURN     all  --  0.0.0.0/0            0.0.0.0/0

Chain KIND-MASQ-AGENT (1 references)
target     prot opt source               destination
RETURN     all  --  0.0.0.0/0            10.244.0.0/16        /* kind-masq-agent: local traffic is not subject to MASQUERADE */
MASQUERADE  all  --  0.0.0.0/0            0.0.0.0/0            /* kind-masq-agent: outbound traffic is subject to MASQUERADE (must be last in chain) */

Chain KUBE-EXT-2CMXP7HKUVJN7L6M (2 references)
target     prot opt source               destination
KUBE-MARK-MASQ  all  --  0.0.0.0/0            0.0.0.0/0            /* masquerade traffic for default/nginx external destinations */
KUBE-SVC-2CMXP7HKUVJN7L6M  all  --  0.0.0.0/0            0.0.0.0/0

Chain KUBE-KUBELET-CANARY (0 references)
target     prot opt source               destination

Chain KUBE-MARK-DROP (0 references)
target     prot opt source               destination
MARK       all  --  0.0.0.0/0            0.0.0.0/0            MARK or 0x8000

Chain KUBE-MARK-MASQ (16 references)
target     prot opt source               destination
MARK       all  --  0.0.0.0/0            0.0.0.0/0            MARK or 0x4000

Chain KUBE-NODEPORTS (1 references)
target     prot opt source               destination
KUBE-EXT-2CMXP7HKUVJN7L6M  tcp  --  0.0.0.0/0            0.0.0.0/0            /* default/nginx */ tcp dpt:30121

Chain KUBE-POSTROUTING (1 references)
target     prot opt source               destination
RETURN     all  --  0.0.0.0/0            0.0.0.0/0            mark match ! 0x4000/0x4000
MARK       all  --  0.0.0.0/0            0.0.0.0/0            MARK xor 0x4000
MASQUERADE  all  --  0.0.0.0/0            0.0.0.0/0            /* kubernetes service traffic requiring SNAT */ random-fully

Chain KUBE-PROXY-CANARY (0 references)
target     prot opt source               destination

Chain KUBE-SEP-GA5F2M4O5E56ZCK4 (1 references)
target     prot opt source               destination
KUBE-MARK-MASQ  all  --  10.244.0.3           0.0.0.0/0            /* default/nginx */
DNAT       tcp  --  0.0.0.0/0            0.0.0.0/0            /* default/nginx */ tcp to:10.244.0.3:80

Chain KUBE-SEP-HUXRPHBUO34UVPEF (1 references)
target     prot opt source               destination
KUBE-MARK-MASQ  all  --  10.244.2.2           0.0.0.0/0            /* default/nginx */
DNAT       tcp  --  0.0.0.0/0            0.0.0.0/0            /* default/nginx */ tcp to:10.244.2.2:80

Chain KUBE-SEP-I7J7B2HF6PITCMSA (1 references)
target     prot opt source               destination
KUBE-MARK-MASQ  all  --  10.244.1.3           0.0.0.0/0            /* default/nginx */
DNAT       tcp  --  0.0.0.0/0            0.0.0.0/0            /* default/nginx */ tcp to:10.244.1.3:80

Chain KUBE-SEP-IT2ZTR26TO4XFPTO (1 references)
target     prot opt source               destination
KUBE-MARK-MASQ  all  --  10.244.0.2           0.0.0.0/0            /* kube-system/kube-dns:dns-tcp */
DNAT       tcp  --  0.0.0.0/0            0.0.0.0/0            /* kube-system/kube-dns:dns-tcp */ tcp to:10.244.0.2:53

Chain KUBE-SEP-L3TEFOPRMLPWJIHV (1 references)
target     prot opt source               destination
KUBE-MARK-MASQ  all  --  10.244.1.2           0.0.0.0/0            /* default/nginx */
DNAT       tcp  --  0.0.0.0/0            0.0.0.0/0            /* default/nginx */ tcp to:10.244.1.2:80

Chain KUBE-SEP-N4G2XR5TDX7PQE7P (1 references)
target     prot opt source               destination
KUBE-MARK-MASQ  all  --  10.244.0.2           0.0.0.0/0            /* kube-system/kube-dns:metrics */
DNAT       tcp  --  0.0.0.0/0            0.0.0.0/0            /* kube-system/kube-dns:metrics */ tcp to:10.244.0.2:9153

Chain KUBE-SEP-OI7NWIH7RAT2AOGF (1 references)
target     prot opt source               destination
KUBE-MARK-MASQ  all  --  10.244.2.3           0.0.0.0/0            /* default/nginx */
DNAT       tcp  --  0.0.0.0/0            0.0.0.0/0            /* default/nginx */ tcp to:10.244.2.3:80

Chain KUBE-SEP-P33L4LSPB7KVLSK6 (1 references)
target     prot opt source               destination
KUBE-MARK-MASQ  all  --  10.244.1.4           0.0.0.0/0            /* default/nginx */
DNAT       tcp  --  0.0.0.0/0            0.0.0.0/0            /* default/nginx */ tcp to:10.244.1.4:80

Chain KUBE-SEP-TAB3TNQU36FOVLKX (1 references)
target     prot opt source               destination
KUBE-MARK-MASQ  all  --  192.168.59.130       0.0.0.0/0            /* default/kubernetes:https */
DNAT       tcp  --  0.0.0.0/0            0.0.0.0/0            /* default/kubernetes:https */ tcp to:192.168.59.130:8443

Chain KUBE-SEP-YIL6JZP7A3QYXJU2 (1 references)
target     prot opt source               destination
KUBE-MARK-MASQ  all  --  10.244.0.2           0.0.0.0/0            /* kube-system/kube-dns:dns */
DNAT       udp  --  0.0.0.0/0            0.0.0.0/0            /* kube-system/kube-dns:dns */ udp to:10.244.0.2:53

Chain KUBE-SERVICES (2 references)
target     prot opt source               destination
KUBE-SVC-2CMXP7HKUVJN7L6M  tcp  --  0.0.0.0/0            10.103.173.168       /* default/nginx cluster IP */ tcp dpt:80
KUBE-EXT-2CMXP7HKUVJN7L6M  tcp  --  0.0.0.0/0            192.168.59.131       /* default/nginx loadbalancer IP */ tcp dpt:80
KUBE-SVC-NPX46M4PTMTKRN6Y  tcp  --  0.0.0.0/0            10.96.0.1            /* default/kubernetes:https cluster IP */ tcp dpt:443
KUBE-SVC-TCOU7JCQXEZGVUNU  udp  --  0.0.0.0/0            10.96.0.10           /* kube-system/kube-dns:dns cluster IP */ udp dpt:53
KUBE-SVC-ERIFXISQEP7F7OF4  tcp  --  0.0.0.0/0            10.96.0.10           /* kube-system/kube-dns:dns-tcp cluster IP */ tcp dpt:53
KUBE-SVC-JD5MR3NA4I4DYORP  tcp  --  0.0.0.0/0            10.96.0.10           /* kube-system/kube-dns:metrics cluster IP */ tcp dpt:9153
KUBE-NODEPORTS  all  --  0.0.0.0/0            0.0.0.0/0            /* kubernetes service nodeports; NOTE: this must be the last rule in this chain */ ADDRTYPE match dst-type LOCAL

Chain KUBE-SVC-2CMXP7HKUVJN7L6M (2 references)
target     prot opt source               destination
KUBE-MARK-MASQ  tcp  -- !10.244.0.0/16        10.103.173.168       /* default/nginx cluster IP */ tcp dpt:80
KUBE-SEP-GA5F2M4O5E56ZCK4  all  --  0.0.0.0/0            0.0.0.0/0            /* default/nginx -> 10.244.0.3:80 */ statistic mode random probability 0.16666666651
KUBE-SEP-L3TEFOPRMLPWJIHV  all  --  0.0.0.0/0            0.0.0.0/0            /* default/nginx -> 10.244.1.2:80 */ statistic mode random probability 0.20000000019
KUBE-SEP-I7J7B2HF6PITCMSA  all  --  0.0.0.0/0            0.0.0.0/0            /* default/nginx -> 10.244.1.3:80 */ statistic mode random probability 0.25000000000
KUBE-SEP-P33L4LSPB7KVLSK6  all  --  0.0.0.0/0            0.0.0.0/0            /* default/nginx -> 10.244.1.4:80 */ statistic mode random probability 0.33333333349
KUBE-SEP-HUXRPHBUO34UVPEF  all  --  0.0.0.0/0            0.0.0.0/0            /* default/nginx -> 10.244.2.2:80 */ statistic mode random probability 0.50000000000
KUBE-SEP-OI7NWIH7RAT2AOGF  all  --  0.0.0.0/0            0.0.0.0/0            /* default/nginx -> 10.244.2.3:80 */

Chain KUBE-SVC-ERIFXISQEP7F7OF4 (1 references)
target     prot opt source               destination
KUBE-MARK-MASQ  tcp  -- !10.244.0.0/16        10.96.0.10           /* kube-system/kube-dns:dns-tcp cluster IP */ tcp dpt:53
KUBE-SEP-IT2ZTR26TO4XFPTO  all  --  0.0.0.0/0            0.0.0.0/0            /* kube-system/kube-dns:dns-tcp -> 10.244.0.2:53 */

Chain KUBE-SVC-JD5MR3NA4I4DYORP (1 references)
target     prot opt source               destination
KUBE-MARK-MASQ  tcp  -- !10.244.0.0/16        10.96.0.10           /* kube-system/kube-dns:metrics cluster IP */ tcp dpt:9153
KUBE-SEP-N4G2XR5TDX7PQE7P  all  --  0.0.0.0/0            0.0.0.0/0            /* kube-system/kube-dns:metrics -> 10.244.0.2:9153 */

Chain KUBE-SVC-NPX46M4PTMTKRN6Y (1 references)
target     prot opt source               destination
KUBE-MARK-MASQ  tcp  -- !10.244.0.0/16        10.96.0.1            /* default/kubernetes:https cluster IP */ tcp dpt:443
KUBE-SEP-TAB3TNQU36FOVLKX  all  --  0.0.0.0/0            0.0.0.0/0            /* default/kubernetes:https -> 192.168.59.130:8443 */

Chain KUBE-SVC-TCOU7JCQXEZGVUNU (1 references)
target     prot opt source               destination
KUBE-MARK-MASQ  udp  -- !10.244.0.0/16        10.96.0.10           /* kube-system/kube-dns:dns cluster IP */ udp dpt:53
KUBE-SEP-YIL6JZP7A3QYXJU2  all  --  0.0.0.0/0            0.0.0.0/0            /* kube-system/kube-dns:dns -> 10.244.0.2:53 */
$

---