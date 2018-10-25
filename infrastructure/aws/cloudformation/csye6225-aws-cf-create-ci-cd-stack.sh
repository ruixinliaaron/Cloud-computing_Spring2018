#! /bin/bash
echo "Input the stack name which you want to create"
read name
<<<<<<< HEAD
aws cloudformation create-stack --stack-name $name --template-body file://./csye6225-cf-ci-cd.json --parameters "ParameterKey=stack,ParameterValue=$name"

echo Create success!
=======
aws cloudformation create-stack --stack-name $name --template-body file://csye6225-cf-ci-cd.json --capabilities CAPABILITY_IAM --parameters "ParameterKey=stackName,ParameterValue=$name"
>>>>>>> assignment06
