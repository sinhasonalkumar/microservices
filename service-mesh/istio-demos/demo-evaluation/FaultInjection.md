
# Fault Injection

```kubectl apply -f samples/bookinfo/networking/virtual-service-all-v1.yaml```

```kubectl apply -f samples/bookinfo/networking/virtual-service-reviews-test-v2.yaml```

With the above configuration, this is how requests flow:

productpage → reviews:v2 → ratings (only for user jason)

productpage → reviews:v1 (for everyone else)

# Injecting an HTTP delay fault

Create a fault injection rule to delay traffic coming from the test user jason.

```kubectl apply -f samples/bookinfo/networking/virtual-service-ratings-test-delay.yaml```


```kubectl get virtualservice ratings -o yaml```


