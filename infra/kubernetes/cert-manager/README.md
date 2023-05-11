# cert-manager demo

### Step-1 : Start minikube.

### Step-2 : Enable and Configure metallb addon (https://medium.com/@shoaib_masood/metallb-network-loadbalancer-minikube-335d846dfdbe).

### Step-3 : Install nginx controller

```
alias k=kubectl
```
```
helm upgrade --install ingress-nginx ingress-nginx \
  --repo https://kubernetes.github.io/ingress-nginx \
  --namespace ingress-nginx --create-namespace 
```

```
k get ns
```

```
k get all -n ingress-nginx
```

```
k get ingressclass
```

### Step-4 : Install cert-manager

```
helm repo add jetstack https://charts.jetstack.io
```

```
helm repo update
```

```
helm install \
  cert-manager jetstack/cert-manager \
  --namespace cert-manager \
  --create-namespace \
  --version v1.11.0 \
  --set installCRDs=true
```

```
k get ns
```

```
 k get all -n cert-manager
```

### Step-5 : Deploy echoserver and clusterIssuer to issue TLS cert and apply it.

```
k create ns echoserver
```

```
k apply -f ./manifest
```

```
k get secret -n echoserver -o yaml
```

```
k get ing -n echoserver
```

```
k describe ing echoserver -n echoserver
```

```
k describe ing echoserver -n echoserver
```

```
k get svc ingress-nginx-controller -n ingress-nginx
```

### Step-6 :  Grab external IP of ingress-nginx-controller provided by metallb and do hosts file entry for the echo server domain in the ingress resource (echo.mycompany.com)

```
externalIP=`k get svc ingress-nginx-controller -n ingress-nginx | awk -F " " '{print $4}' | xargs | awk -F " " '{print $2}'`
```
```
echo $externalIP echo.mycompany.com | sudo tee -a /etc/hosts
```

```
cat /etc/hosts
```

```
curl -k https://echo.mycompany.com | jq .
```

```
curl -kL http://echo.mycompany.com | jq .
```


### Step-7 : Go browser and test https://echo.mycompany.com

### Step-8 : Look for the TLS cert. It is left signed cert issue by acme using cert-maanger as we are using https://acme-staging-v02.api.letsencrypt.org/directory



