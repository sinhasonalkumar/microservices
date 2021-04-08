# Steps To Install Istio On minikube

[Install Istio](https://www.istio.io/latest/docs/setup/getting-started/)

## Or Run Undernoted Command Step By Step

### Step-1 : Go to a directory location where you want to download istio binary

### Step-2 : start minikube

```
minikube start --cpus 4 --memory 8192
```

### Step-3 : Run

```
curl -L https://istio.io/downloadIstio | sh -
```

### Step-4 : Run

```
cd istio-1.9.1
```

### Step-5 : Run

```
export PATH=$PWD/bin:$PATH
```

### Step-6 : Run

```
istioctl install --set profile=demo -y
```

### Step-7 : Run

```
kubectl label namespace default istio-injection=enabled
```

### Step-8 : Open another terminal and run

```
minikube tunnel
```

### Step-9 : Let it run alongwith other previously opened terminal


### Step-10 : Run

```
kubectl get svc istio-ingressgateway -n istio-system
```


### Step-9 : Run

```
kubectl apply -f samples/addons/kiali.yaml
```

```
kubectl apply -f samples/addons/jaeger.yaml
```

```
kubectl apply -f samples/addons/prometheus.yaml
```

```
kubectl apply -f samples/addons/grafana.yaml
```

