# Steps Install Istio

[Install Istio](https://www.istio.io/latest/docs/setup/getting-started/)

## Or Run Undernoted Command Step By Step

### Step-1 : Go to a directory location where you want to download istio binary

### Step-2 : Run

```
curl -L https://istio.io/downloadIstio | sh -
```

### Step-2 : Run

```
cd istio-1.9.1
```

### Step-3 : Run

```
export PATH=$PWD/bin:$PATH
```

### Step-4 : Run

```
istioctl install --set profile=demo -y
```

### Step-5 : Run

```
kubectl label namespace default istio-injection=enabled
```

### Step-6 : Open another terminal and run

```
minikube tunnel
```

### Step-7 : Let it run alongwith other previously opened terminal


### Step-8 : Run

```
kubectl get svc istio-ingressgateway -n istio-system
```


### Step-9 : Run

```
kubectl apply -f samples/addons/kiali.yam
```

```
kubectl apply -f samples/addons/jaeger.yaml
```

```
kubectl apply -f samples/addons/prometheus.yaml
```

```
apply -f samples/addons/grafana.yaml
```

