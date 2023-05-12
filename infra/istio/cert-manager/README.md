# cert-manager demo

### Step-1 : Install cert-manager

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

 ### Step-2 : Deploy echoservice

 ```
k create ns echoserver
```

```
k apply -f ./manifest/workload
```

```
k get all -n echoserver
```

```
k get secret -n istio-system
```

 ### Step-3 : Apply tls manifest

 ```
k apply -f ./manifest/tls
```

```
k get secret -n istio-system
```

```
k get certificaterequests -n istio-system
```

```
k get certificate -n istio-system

```

### Step-4 : Apply istio routing

 ```
k apply -f ./manifest/routing
```
