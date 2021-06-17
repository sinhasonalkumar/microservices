# POD Scheduling DEMO

## Using Kind For Provisioning multi-node K8 Cluster

```
https://kind.sigs.k8s.io/docs/user/quick-start 
```

## Step-1 : Create kind cluster config file named kind-4-node-k8-cluster-config.yaml file. 

### Place undernoted content in the file kind-4-node-k8-cluster-config.yaml

```
# four node (two workers) cluster config
kind: Cluster
apiVersion: kind.x-k8s.io/v1alpha4
nodes:
- role: control-plane
- role: worker
- role: worker
- role: worker

```

## Step-2 : Execute undernoted command to provision 4 node K8 cluster

```
kind create cluster --config kind-4-node-k8-cluster-config.yaml

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

### Command to assign multiple labels to node

```
kubectl label nodes kind-worker env=dev app=java
```












