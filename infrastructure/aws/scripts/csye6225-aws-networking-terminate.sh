#!/bin/bash
export PATH=~/.local/bin:$PATH
source ~/.bash_profile
NOW=$(date +"%F")
echo $NOW
echo any stack to be deleted?
read stackname
vpcname=$stackname'-csye6225-vpc'
igwname=$stackname'-csye6225-InternetGateway'
rtbname=$stackname'-csye6225-public-route-table'

vpcId=`aws ec2 describe-tags --filters "Name=value,Values=$vpcname" --query 'Tags[0].ResourceId' --output text`
igwId=`aws ec2 describe-tags --filters "Name=value,Values=$igwname" --query 'Tags[0].ResourceId' --output text`
rtbId=`aws ec2 describe-tags --filters "Name=value,Values=$rtbname" --query 'Tags[0].ResourceId' --output text`

echo Deleting Route 
aws ec2   delete-route --route-table-id $rtbId --destination-cidr-block 0.0.0.0/0
echo Route is Deleted!

echo Deleting RouteTable $rtbname : 
aws ec2   delete-route-table --route-table-id $rtbId
echo RouteTable is Deleted!

echo Detaching IGW $igwname : $igwId & VPC $vpcname : $vpcId
aws ec2 detach-internet-gateway --internet-gateway-id $igwId  --vpc-id $vpcId
echo Detachment has done!

echo Deleting IGW $igwname : $igwId
aws ec2   delete-internet-gateway --internet-gateway-id $igwId
echo IGW is Deleted!

echo Terminating VPC $vpcname : $vpcId
aws ec2   delete-vpc --vpc-id   $vpcId
echo VPC is Terminated!
