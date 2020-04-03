# Getting Started With Istio On Kubernetes Using Docker Desktop
## Reference 
https://istio.io/docs/setup/getting-started/

## Install Istio

```
istioctl manifest apply --set profile=demo

```

## Add a namespace label to instruct Istio to automatically inject Envoy sidecar proxies when you deploy your application later

```
kubectl label namespace default istio-injection=enabled

```


## Deploy the sample application

([bookinfo.yaml](samples/bookinfo/platform/kube/bookinfo.yaml))

```
kubectl apply -f samples/bookinfo/platform/kube/bookinfo.yaml

```


## The application will start. As each pod becomes ready, the Istio sidecar will deploy along with it.

Check services deployed

```
kubectl get services
```

Check pods deployed

```
kubectl get pods

```

## Verify everything is working correctly up to this point. 


```
kubectl exec -it $(kubectl get pod -l app=ratings -o jsonpath='{.items[0].metadata.name}') -c ratings -- curl productpage:9080/productpage | grep -o "<title>.*</title>"


```

## Open the application to outside traffic


Associate this application with the Istio gateway

([bookinfo-gateway.yaml](samples/bookinfo/networking/bookinfo-gateway.yaml))

```
kubectl apply -f samples/bookinfo/networking/bookinfo-gateway.yaml
```

Confirm the gateway has been created

```
kubectl get gateway
```

view ingress gateway

```
kubectl -n istio-system get service istio-ingressgateway

```

## Generate Load

docker run fortio/fortio load -qps 10 -c 20 -t 5m http://192.168.0.104/productpage



## Dashboards

Kiali

```
istioctl dashboard kiali
```


Prometheus

```
istioctl dashboard prometheus
```

Prometheus

```
istioctl dashboard grafana
```

Jaeger

```
istioctl dashboard jaeger
```

