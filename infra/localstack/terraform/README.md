#Step 1: Start LocalStack

```
localstack start
```

#Step 2: Configure AWS CLI

```
cat >> ~/.aws/config << EOL
[profile mac_localstack]
aws_access_key_id = test
aws_secret_access_key = test
region = us-east-1
EOL

#Step: 3:

```
export AWS_DEFAULT_PROFILE=mac_localstack
```

#Step 4: Test localstack dynamodb

```
awslocal dynamodb list-tables
```

#Step 5: Chang directory to stack directory

```
cd stack
```

#Step 6: Tarraform plan

```
terraform plan -out tfplan
```

#Step 7: Tarraform apply

```
terraform apply tfplan
```

#Step 8: Test localstack dynamodb

```
awslocal dynamodb list-tables
```


#Step 9: Plan Destory

```
terraform plan --destroy --out tfplan
```

#Step 10: Apply Destroyed plan

```
terraform apply tfplan
```

#Step 11: Test localstack dynamodb

```
awslocal dynamodb list-tables
```

