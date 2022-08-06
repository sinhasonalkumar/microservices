# Steps To Try It

### Step-1 : Install external-secrets operator 
```
helm install external-secrets \
   external-secrets/external-secrets \
    -n external-secrets \
    --create-namespace
```
### Step-2 : Install Stakater Operator
```
kubectl apply -f https://raw.githubusercontent.com/stakater/Reloader/master/deployments/kubernetes/reloader.yaml
```

### Step-3 : Create Seceret in AWS Secert Manager

```
aws secretsmanager create-secret --region us-east-1 --name org/bu/domain/a-service/app/aSecret --secret-string '{"org/bu/domain/a-service/app/aSecret"}'
```
**_NOTE:_** We need to have better and secured process to create and update secerts in secret-manager. This is done for POC purpose as it out of scope with external-secrets perpective. I will create another document for life-cycle of secret management in AWS Secret Manager. 

### Step-4 : Create IAM Read-Only Policy to Access this secret using file  __[external-secrets-eks-demo.json](./iam/external-secrets-eks-demo.json)__

```
  aws iam create-policy \
                            --region us-east-1 \
                            --policy-name external-secrets-eks-demo \
                            --policy-document file://external-secrets-eks-demo.json 
```


### Step-5 : IRSA -> Create IAM Assume Role with the trust relationship using file __[trust-relationship.json](./iam/trust-relationship.json)__ and attach the role created in step-4.

```
aws iam create-role \
                            --region us-east-1 \
                            --role-name external-secrets-eks-demo \
                            --assume-role-policy-document file://trust-relationship.json
```

```
aws iam attach-role-policy \
                            --role-name external-secrets-eks-demo \
                            --policy-arn=arn:aws:iam::[AccountNumber]:policy/external-secrets-eks-demo
```

### Step-6 : Create Kubernetes Service Account by annotationg Role ARN that has been created in Step-5 (__[external-secrets-eks-demo-sa.yaml](./external-secrets-eks-demo-sa.yaml)__) 

```
kubectl apply -f external-secrets-eks-demo-sa.yaml
```

### Step-7 : Create SecretStore Custom Resource using the ServiceAccount created in Step-6 which will pull secret from AWS Seceret Manager based on the IAM policy defined (__[aws-secret-store.yaml](./aws-secret-store.yaml)__). 

```
kubectl apply -f aws-secret-store.yaml
```

**_NOTE:_** In the non fake provider like AWS Secret Manager or HashiCorp valut, you just have to rotate secret and rotated secert will reflect the running pods. 

### Step-8 : Create ExternalSecret Custom Resource to transalate it to Kubnernates Native Secret Resource. This step will generate Kubernetes secert (__[external-secret.yaml](./external-secret.yaml)__).

```
watch "kubectl get secrets" 

```

```
kubectl apply -f external-secret.yaml
```

### Step-9 : Use auto generated kubernetes secrets in undernoted manifest file (__[nginx.yaml](./nginx.yaml)__).

```
kubectl apply -f nginx.yaml
```

### Step-10 : Use auto generated kubernetes secrets in undernoted manifest file (__[nginx-with-stakater-reloader.yaml](./nginx-with-stakater-reloader.yaml)__).

**_Gotcha:_** If the secrets are injected as file (secret as mounted volume) then rotated secret key will be reflecetd to pod automatically with rolling restart of pods. But if they are injected as environment variable then rolling restart is required. 

**Note** This deployment is using Stakater to peform rolling restart upon any update on generated kubernetes secret resource in step-8

```
kubectl apply -f nginx-with-stakater-reloader.yaml
```

### Step-11 : Monitoring injected secrets as File on a volume mount and as Environment Variable. 

```
while true; do kubectl exec -it deploy/nginx -- cat /var/app-secret/my_secret_key ; echo "\n"  ; sleep 1; done;
```

```
while true; do kubectl exec -it deploy/nginx -- printenv |grep -i MY_SECRET_KEY ; echo "\n"  ; sleep 1; done;
```

```
while true; do kubectl exec -it deploy/nginx-reloaded -- cat /var/app-secret/my_secret_key ; echo "\n"  ; sleep 1; done;
```
```
while true; do kubectl exec -it deploy/nginx-reloaded -- printenv |grep -i MY_SECRET_KEY ; echo "\n"  ; sleep 1; done;
```
### Step-11 : Rotate Secret In AWS Secret Manger and it should automatically refelect in Kuberntes Secert and Deployed both nginx applications.

```
aws secretsmanager put-secret-value --region=us-east-1  --secret-id arn:aws:secretsmanager:us-east-1:[AccountNumber]:secret:org/bu/domain/a-service/app/aSecret-Ax6z9B --secret-string '{"org/bu/domain/a-service/app/aSecret":"PasswordRotated"}'
```
