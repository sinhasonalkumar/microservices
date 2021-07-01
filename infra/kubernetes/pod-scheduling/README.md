# POD Scheduling DEMO

## Using Kind For Provisioning multi-node K8 Cluster

```
https://kind.sigs.k8s.io/docs/user/quick-start 
```

## Step-1 : Create kind cluster config file named kind-5-node-k8-cluster-config.yaml file. 

### Place undernoted content in the file kind-5-node-k8-cluster-config.yaml

```
# five nodes (four workers) cluster config
kind: Cluster
apiVersion: kind.x-k8s.io/v1alpha4
nodes:
- role: control-plane
- role: worker
- role: worker
- role: worker
- role: worker

```

## Step-2 : Execute undernoted command to provision 4 node K8 cluster

```
kind create cluster --config kind-5-node-k8-cluster-config.yaml

```

## Setp 3 : Once Cluster privioning is complete then execute undernoted command to confirm 

```
kubectl get nodes 
```

### With Lables

```
kubectl get nodes --show-labels
```

## Setp 4 : Add labels To Worker Nodes  

```
kubectl label nodes kind-worker env=dev
```
```
kubectl label nodes kind-worker2 env=prod
```
```
kubectl label nodes kind-worker3 env=prod
```

```
kubectl label nodes kind-worker4 env=prod
```

## Setp 5 : Check Node Labels


```
kubectl get nodes --show-labels
```

```
kubectl get nodes --show-labels -l env=prod
```

```
kubectl get nodes --show-labels -l env=dev
```

OR 

```
kubectl get nodes -l env=prod
```

```
kubectl get nodes -l env=dev
```

## Step 6 : Create Taint To Worker Node

```
kubectl taint nodes kind-worker2 workLoad=prod:NoSchedule
```

```
kubectl taint nodes kind-worker3 workLoad=prod:NoSchedule
```

```
kubectl taint nodes kind-worker4 workLoad=prod:NoSchedule
```

OR

```
kubectl taint nodes -l env=prod workLoad=prod:NoSchedule
```


## Step 7 : Describe Node To See Taints

```
kubectl describe nodes kind-worker2
```

```
kubectl describe nodes kind-worker3
```

```
kubectl describe nodes kind-worker4
```

OR 

```
kubectl describe nodes kind-worker2|grep -i taint
```

```
kubectl describe nodes kind-worker3|grep -i taint
```

```
kubectl describe nodes kind-worker4|grep -i taint
```

## Note : 

### Command To Delete Label 

```
kubectl label nodes kind-worker env-
```
```
kubectl label nodes kind-worker2 env-
```
```
kubectl label nodes kind-worker3 env-
```

```
kubectl label nodes kind-worker4 env-
```

### Command to assign multiple labels to node

```
kubectl label nodes kind-worker env=dev app=java
```

### Command to remove taint

```
kubectl taint nodes kind-worker2 workLoad-
```


```
kubectl taint nodes kind-worker3 workLoad-
```

```
kubectl taint nodes kind-worker4 workLoad-
```

OR


```
kubectl taint nodes -l env=prod workLoad-
```












