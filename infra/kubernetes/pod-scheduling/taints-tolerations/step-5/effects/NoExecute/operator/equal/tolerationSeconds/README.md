## Step-1 

```
kubectl apply -f deployment-web-server.yaml
```

## Step-2

```
kubectl taint nodes kind-worker4 mem=high:NoExecute
```

pod will be removed from kind-worker4 after 30 seconds

## Step-3 

```
kubectl delete -f deployment-web-server.yaml
```

## Step-4 

```
kubectl apply -f deployment-web-server.yaml
```

Pod will not be schedule on kind-worker4


## Step-5

```
kubectl delete -f deployment-web-server.yaml
```

```
kubectl taint nodes kind-worker4 mem-
```