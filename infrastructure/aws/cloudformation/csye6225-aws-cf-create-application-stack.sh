#! /bin/bash
echo "Input the stack name which you want to create"
read name

echo "Input the stack name of network stack"
read stackname

vpcname=$stackname'-csye6225-vpc'
websubnetname=$stackname'-csye6225-webSubnet'
dbsubnetname=$stackname'-csye6225-dbSubnet'
groupname='csye6225-webapps'

VPCID=`aws ec2 describe-tags --filters "Name=value,Values=$vpcname" --query 'Tags[0].ResourceId' --output text`
webSubnetID=`aws ec2 describe-tags --filters "Name=value,Values=$websubnetname" --query 'Tags[0].ResourceId' --output text`
dbSubnetID=`aws ec2 describe-tags --filters "Name=value,Values=$dbsubnetname" --query 'Tags[0].ResourceId' --output text`
GROUPID=`aws ec2 describe-tags --filters "Name=value,Values=$groupname" --query 'Tags[0].ResourceId' --output text`
aws cloudformation create-stack --stack-name $name --capabilities CAPABILITY_NAMED_IAM --template-body file://csye6225-cf-application.json --parameters "ParameterKey=stackName,ParameterValue=$name" "ParameterKey=VPCID,ParameterValue=$VPCID" "ParameterKey=webSubnetID,ParameterValue=$webSubnetID" "ParameterKey=dbSubnetID,ParameterValue=$dbSubnetID" "ParameterKey=SecurityGroupID,ParameterValue=$GROUPID"
