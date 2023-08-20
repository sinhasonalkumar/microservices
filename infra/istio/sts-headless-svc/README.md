# Setp-1

```
kubectl apply -f .

```

# Setp-2

```
kubectl apply -f ./app-network

```

### Gotcha : Always deploy ServiceEntry after deploying sts and headless service so that ServiceEntry can resolve fqdn of pods via headless service