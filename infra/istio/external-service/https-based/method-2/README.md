# Step-1


### https://istio.io/latest/docs/tasks/traffic-management/ingress/secure-ingress/
### https://octopus.com/blog/istio/istio-serviceentry


```
mkdir mycompanycert
```
```
openssl req -x509 -sha256 -nodes -days 365 -newkey rsa:2048 -subj '/O=mycompany Inc./CN=mycompany.com' -keyout mycompanycert/mycompany.com.key -out mycompanycert/mycompany.com.crt
```

```
openssl req -out mycompanycert/api.mycompany.com.csr -newkey rsa:2048 -nodes -keyout mycompanycert/api.mycompany.com.key -subj "/CN=api.mycompany.com/O=api organization"
```
```
openssl x509 -req -sha256 -days 365 -CA mycompanycert/mycompany.com.crt -CAkey mycompanycert/mycompany.com.key -set_serial 0 -in mycompanycert/api.mycompany.com.csr -out mycompanycert/api.mycompany.com.crt
```

```
kubectl create -n istio-system secret tls api-mycompany-credential \
  --key=mycompanycert/api.mycompany.com.key \
  --cert=mycompanycert/api.mycompany.com.crt
```

# Step-2

```
kubectl apply -f .
```

# Step-3

update /etc/hosts Istio Ingres GW Service external IP against domain api.mycompany.com


# Step-4

https://api.mycompany.com/

#### trust this self signed cert
