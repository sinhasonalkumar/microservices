
# Fault Injection

([virtual-service-all-v1.yaml](samples/bookinfo/networking/virtual-service-all-v1.yaml))

```kubectl apply -f samples/bookinfo/networking/virtual-service-all-v1.yaml```

```kubectl apply -f samples/bookinfo/networking/virtual-service-reviews-test-v2.yaml```

With the above configuration, this is how requests flow:

productpage → reviews:v2 → ratings (only for user jason)

productpage → reviews:v1 (for everyone else)

# Injecting an HTTP delay fault

Create a fault injection rule to delay traffic coming from the test user jason.

([virtual-service-ratings-test-delay.yaml](samples/bookinfo/networking/virtual-service-ratings-test-delay.yaml))

```kubectl apply -f samples/bookinfo/networking/virtual-service-ratings-test-delay.yaml```


```kubectl get virtualservice ratings -o yaml```


route 100% of the traffic to reviews:v3 by applying this virtual service:

([virtual-service-reviews-v3.yaml] (samples/bookinfo/networking/virtual-service-reviews-v3.yaml))

```kubectl apply -f samples/bookinfo/networking/virtual-service-reviews-v3.yaml```


# Injecting an HTTP abort fault

([virtual-service-ratings-test-abort.yaml](samples/bookinfo/networking/virtual-service-ratings-test-abort.yaml))

``` kubectl apply -f samples/bookinfo/networking/virtual-service-ratings-test-abort.yaml  ```


``` kubectl get virtualservice ratings -o yaml ```



# Cleanup

kubectl delete -f samples/bookinfo/networking/virtual-service-all-v1.yaml




