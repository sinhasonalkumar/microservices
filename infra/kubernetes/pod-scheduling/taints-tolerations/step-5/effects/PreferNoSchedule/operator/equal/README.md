## Step-1 

```
kubectl apply -f deployment-web-server.yaml
```

## Step-2

```
kubectl taint nodes kind-worker4 mem=high:PreferNoSchedule
```

pod will not be removed from kind-worker4

## Step-3 

```
kubectl delete -f deployment-web-server.yaml
```

## Step-4 

```
kubectl apply -f deployment-web-server.yaml
```

Pod may or may not be schedule on kind-worker4


## Step-5

```
kubectl delete -f deployment-web-server.yaml
```

```
kubectl taint nodes kind-worker4 mem-
```