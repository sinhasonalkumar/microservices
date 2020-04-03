# Request Routing

## Apply Default Destination Rules.

```kubectl apply -f samples/bookinfo/networking/destination-rule-all.yaml```

```kubectl apply -f samples/bookinfo/networking/destination-rule-all-mtls.yaml```


## Apply a virtual service

```kubectl apply -f samples/bookinfo/networking/virtual-service-all-v1.yaml```

```kubectl get virtualservices -o yaml```

```kubectl get destinationrules -o yaml```

 Notice that the reviews part of the page displays with no rating stars, no matter how many times you refresh. This is because you configured Istio to route all traffic for the reviews service to the version reviews:v1 and this version of the service does not access the star ratings service.

## Route based on user identity

Next, you will change the route configuration so that all traffic from a specific user is routed to a specific service version. In this case, all traffic from a user named Jason will be routed to the service reviews:v2.

```kubectl apply -f samples/bookinfo/networking/virtual-service-reviews-test-v2.yaml```

``` kubectl get virtualservice reviews -o yaml ```

On the /productpage of the Bookinfo app, log in as user jason.
Refresh the browser. What do you see? The star ratings appear next to each review.

Log in as another user (pick any name you wish).
Refresh the browser. Now the stars are gone. This is because traffic is routed to reviews:v1 for all users except Jason.

## Cleanup

```kubectl delete -f samples/bookinfo/networking/virtual-service-all-v1.yaml```