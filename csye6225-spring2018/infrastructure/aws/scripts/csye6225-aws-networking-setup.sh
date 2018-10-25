#!/bin/bash
export PATH=~/.local/bin:$PATH
source ~/.bash_profile
NOW=$(date +"%F")
echo $NOW

echo Creating VPC With stackName
read stackname
vpcId=`aws ec2 create-vpc --cidr-block 10.0.0.0/16 --query 'Vpc.VpcId' --output text`
echo VPC $vpcId is Created!
aws ec2 create-tags --resources $vpcId --tags Key=Name,Value=$stackname'-csye6225-vpc'

aws ec2 modify-vpc-attribute --vpc-id $vpcId --enable-dns-support "{\"Value\":true}"
aws ec2 modify-vpc-attribute --vpc-id $vpcId --enable-dns-hostnames "{\"Value\":true}"

echo Creating Internet Gateway With stackName
internetGatewayId=`aws ec2 create-internet-gateway --query 'InternetGateway.InternetGatewayId' --output text`
echo Internet GW $internetGatewayId created!

aws ec2 create-tags --resources $internetGatewayId --tags Key=Name,Value=$stackname'-csye6225-InternetGateway'

echo Attaching IGW to VPC
aws ec2 attach-internet-gateway --internet-gateway-id $internetGatewayId --vpc-id $vpcId
echo Attached

echo Creating route-table
routeTableId=`aws ec2 create-route-table --vpc-id $vpcId --query 'RouteTable.RouteTableId' --output text`
aws ec2 create-tags --resources $routeTableId --tags Key=Name,Value=$stackname'-csye6225-public-route-table'
echo Adding default-route to $routeTableId

aws ec2 create-route --route-table-id $routeTableId --destination-cidr-block 0.0.0.0/0 --gateway-id $internetGatewayId

echo Done
