#!/bin/bash
echo "Input the stack name which you want to terminate"
read name

bucketName="code-deploy.csye6225-spring2018-liruix.me"

aws s3 rb s3://$bucketName --force

aws cloudformation delete-stack --stack-name $name
