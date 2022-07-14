``` Steps To Try It ```

### Step-1

```
helm repo add external-secrets https://charts.external-secrets.io
```

### Step-2

```
helm install external-secrets \
   external-secrets/external-secrets \
    -n external-secrets \
    --create-namespace 
```

### Step-3

```
kubectl apply -f cluster-secret-store.yaml
```

### Step-4

```
kubectl apply -f external-secret.yaml
```

### Step-5

```
kubectl apply -f nginx.yaml
```


### Step-6 

```
kubectl get all -n external-system
```

### Step-7

```
kubectl get ClusterSecretStore
kubectl get ExternalSecret
kubectl get pod
```

### Step-8

Corresponding kubernetes Secret Resource will be created by external secrets contoller using ClusterSecretStore (fake backend) and ExternalSecert CRs

### Step-9

```
kubectl get secret app-in-sync-secret

kubectl describe secret app-in-sync-secret

kubectl get secrets app-in-sync-secret -o yaml | yq .data.my-secret_key | base64 -d

kubectl get secrets app-in-sync-secret -o yaml | yq .data | awk -F ":" '{print $2}' | base64 -d

```

### Step-10

Test secret rotation. After secret rotation controller will keep rotated secret in sync and will refresh k8 secret resource and pod will have rotated secret


