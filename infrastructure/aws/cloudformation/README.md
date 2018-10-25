Cloud Formation:

Run commands: Open terminal and run the following commands

assignment3:
To launch : sh create-csye6225-cloudformation-stack.sh 'stackname' where stackname needs to be passed through the command line.
To terminate : sh delete-csye6225-cloudformation-stack.sh 'stackname' where stackname needs to be passed through the command line.
Validate output: check if it was successfully created with the mentioned parameters and later deleted through the delete script.

assignment4:
To launch: sh csye6225-aws-cf-create-application-stack.sh 'stackname' where stackname needs to be passed through the command line.
Before terminating, you would need to manually delete the instance manually from the AWS and then run the teardown script.
To terminate: sh csye6225-aws-cf-terminate-application-stack.sh 'stackname' where stackname needs to be passed through the command line.
Validate output: check if it was successfully created with the mentioned parameters and later deleted through the delete script.
