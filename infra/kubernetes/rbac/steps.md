# Subject : User

## Step1 : generate .key
```
openssl genrsa -out devUser.key 2048
```
## Step2 : generate .csr

```
openssl req -new \
    -key devUser.key \
    -out devUser.csr \
    -subj "/CN=devUser/O=devUsersGroup"
```

## Step3 : ca.crt and ca.key location for minikube

```
ls ~/.minikube/ca.crt
```
```
ls ~/.minikube/ca.key
```

## Step4 : Sign csr with ca cert and ca key of minikube 

```
openssl x509 -req \
    -in devUser.csr \
    -CA ~/.minikube/ca.crt \
    -CAkey ~/.minikube/ca.key \
    -CAcreateserial \
    -out devUser.crt \
    -days 500
```

## Step5 : Configure kubeConfig

### set-credentials
```
kubectl config set-credentials devUser \
    --client-certificate=devUser.crt \
    --client-key=devUser.key
```    
### set-context

```
kubectl config set-context devUser-context \
    --cluster=minikube \
    --namespace=default \
    --user=devUser
```

### view kube config

```
kubectl config view
```

## Step6 : Set current kube context

```
kubectx devUser-context
```
or

```
kubectl config use-context devUser-context
```

### Check current kube context 

```
kubectl config current-context
```

## Step7 : Try to get all pods

```
kubectl get pods
```

### At this point authentication is fine but have no authorization to access any kubernetes resources


## Step8 : Step Roles and RoleBinding For DevUser for giving access to kubernetes resources

### Switch context to minikube 

```
kubectx minikube
```
or

```
kubectl config use-context minikube
```

### Apply Role

```
kubectl apply -f role.yaml
```

### Apply RoleBinding

```
kubectl apply -f role-binding.yaml
```

### view Roles and RoleBinding

```
kubectl get roles,roleBinding reader-access
```

### Switch context to devUser-context 

```
kubectx devUser-context
```
or

```
kubectl config use-context devUser-context
```

## Step9 : Try to get all pods

### Check current kube context 

```
kubectl config current-context
```
### get all pods
```
kubectl get pods
```

### get all services
```
kubectl get svc
```

## Step10 : Try to interact with kube api-srver with client key and client cert 

```
curl -k \
https://192.168.64.10:8443/api
```

```
curl -k \
--cert devUser.crt \
--key devUser.key \
https://192.168.64.10:8443/api
```

```
curl \
--cert devUser.crt \
--key devUser.key \
--cacert ~/.minikube/ca.crt \
https://192.168.64.10:8443/api
```

```
curl \
--cert devUser.crt \
--key devUser.key \
--cacert ~/.minikube/ca.crt \
https://192.168.64.10:8443/api/v1/namespaces/default/pods
```


# Subject : Group

## Step1 : generate .key
```
openssl genrsa -out devUser1.key 2048
```
## Step2 : generate .csr

```
openssl req -new \
    -key devUser1.key \
    -out devUser1.csr \
    -subj "/CN=devUser1/O=devUsersGroup"
```

## Step3 : ca.crt and ca.key location for minikube

```
ls ~/.minikube/ca.crt
```
```
ls ~/.minikube/ca.key
```

## Step4 : Sign csr with ca cert and ca key of minikube 

```
openssl x509 -req \
    -in devUser1.csr \
    -CA ~/.minikube/ca.crt \
    -CAkey ~/.minikube/ca.key \
    -CAcreateserial \
    -out devUser1.crt \
    -days 500
```

## Step5 : Configure kubeConfig

### set-credentials
```
kubectl config set-credentials devUser1 \
    --client-certificate=devUser1.crt \
    --client-key=devUser1.key
```    
### set-context

```
kubectl config set-context devUser1-context \
    --cluster=minikube \
    --namespace=default \
    --user=devUser1
```

### view kube config

```
kubectl config view
```

## Step6 : Set current kube context

```
kubectx devUser1-context
```
or

```
kubectl config use-context devUser1-context
```

### Check current kube context 

```
kubectl config current-context
```

## Step7 : Try to get all pods

```
kubectl get pods
```

### At this point authentication is fine but have no authorization to access any kubernetes resources


## Step8 : Step Roles and RoleBinding For DevUser for giving access to kubernetes resources

### Switch context to minikube 

```
kubectx minikube
```
or

```
kubectl config use-context minikube
```

### Apply Role

```
kubectl apply -f role.yaml
```

### Apply RoleBinding

```
kubectl apply -f role-binding-kind-group.yaml
```

### view Roles and RoleBinding

```
kubectl get roles,roleBinding
```

### Switch context to devUser-context 

```
kubectx devUser1-context
```
or

```
kubectl config use-context devUser1-context
```

## Step9 : Try to get all pods

### Check current kube context 

```
kubectl config current-context
```
### get all pods
```
kubectl get pods
```

### get all services
```
kubectl get svc
```

# Subject : ServiceAccount

## Step1 : Apply Deployment with default Service Account
```
kubectl apply -f rbac-deployment-default-sa.yaml
```
## Step2 : Apply ServiceAccount 

```
kubectl apply -f rbac-service-account.yaml
```

## Step3 : Apply Deployment with Custom Service Account

```
kubectl apply -f rbac-deployment-with-sa.yaml
```

## Step4 : Apply Role and RoleBinding

```
kubectl apply -f role.yaml
```

```
kubectl apply -f role-binding-kind-service-account.yaml
```

## Step5 : ssh into pod deployed with default service account in step1

### Describe pod to get location secret mount to know location of ca.crt and bearer token which will be 

```
/var/run/secrets/kubernetes.io/serviceaccount
```

### ssh into pod
```
cd /var/run/secrets/kubernetes.io/serviceaccount
```

```
token=`cat token`
```

```
curl --cacert ca.crt -H "Authorization: Bearer $token" https://kubernetes/api
```

```
curl --cacert ca.crt -H "Authorization: Bearer $token" https://kubernetes/api/v1/namespaces/defau
lt/pods
```

## Step6 : ssh into pod deployed with custom service account in step3

### Describe pod to get location secret mount to know location of ca.crt and bearer token which will be 

```
/var/run/secrets/kubernetes.io/serviceaccount
```

### ssh into pod
```
cd /var/run/secrets/kubernetes.io/serviceaccount
```

```
token=`cat token`
```

```
curl --cacert ca.crt -H "Authorization: Bearer $token" https://kubernetes/api
```

```
curl --cacert ca.crt -H "Authorization: Bearer $token" https://kubernetes/api/v1/namespaces/defau
lt/pods
```
