### 1
```
alias k=kubectl
```
```
alias k=minikube
```
```
mk addons enable csi-hostpath-driver
```

```
mk addons enable volumesnapshots
```


### 2
```
k get sc,pvc,pv,pods,svc
```

### 3
```
k apply -f ./storage
```

### 4
```
k get sc,pvc,pv,pods,svc
```

### 5
```
k apply -f ./workload
```

### 6
```
k get sc,pvc,pv,pods,svc
```

### 7

```
k exec -it nginx-0 -- bash
```

```
echo "Hello STS With Persistence Storage" > /vol/testPersistenceStorage
```

```
cat  /vol/testPersistenceStorage
```

```
exit
```

### 8
```
k exec -it nginx-0 -- cat  /vol/testPersistenceStorage

```

### 9
```
k exec -it nginx-1 -- cat  /vol/testPersistenceStorage

```

### 10

```
k delete -f ./workload
```

### 11

```
k get sc,pvc,pv,pods,svc
```

### 12

```
k apply -f ./workload
```

### 13

```
k get sc,pvc,pv,pods,svc
```

### 14

```
k exec -it nginx-0 -- cat  /vol/testPersistenceStorage

```

### 15

```
k exec -it nginx-1 -- cat  /vol/testPersistenceStorage

```

### 16

```
k delete -f ./workload
```

### 17
```
k delete -f ./storage
```

### 18

```
k get sc,pvc,pv,pods,svc
```
