# Equality Based and Set-Based Label Selector Demo

## Deploy
```
kubectl apply -f commandDemo
```
### Show Labels

```
kubectl get pods --show-labels
```

### Show Label Colomn 

```
kubectl get pods -L=version
kubectl get pods -L=version,env,app
```

### Equality Based Demo

#### =
```
kubectl get pods -L=version,env,app -l='env=dev'
```
#### ==
```
kubectl get pods -L=version,env,app -l='env==dev'
```
#### !
```
kubectl get pods -L=version,env,app -l='env!=dev'
```

### Set Based Demo

#### In
```
kubectl get pods -L=env,version,app -l='env in (dev)'
kubectl get pods -L=env,version,app -l='env in (dev,qa)'
```
#### NotIn

```
kubectl get pods -L=env,version,app -l='env notin (dev)'
kubectl get pods -L=env,version,app -l='env notin (dev,qa)'
```

#### Exists

```
kubectl get pods -L=env,version,app -l='version'
```

#### DoesNotExist

```
kubectl get pods -L=env,version,app -l='!version'
```







