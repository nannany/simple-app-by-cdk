package com.myorg;

import com.sun.tools.javac.util.List;

import jdk.internal.loader.AbstractClassLoaderValue;
import software.amazon.awscdk.Tags;
import software.amazon.awscdk.services.ec2.CfnInternetGateway;
import software.amazon.awscdk.services.ec2.CfnInternetGatewayProps;
import software.amazon.awscdk.services.ec2.Subnet;
import software.amazon.awscdk.services.ec2.SubnetProps;
import software.amazon.awscdk.services.ec2.Vpc;
import software.amazon.awscdk.services.ec2.VpcProps;
import software.constructs.Construct;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
// import software.amazon.awscdk.Duration;
// import software.amazon.awscdk.services.sqs.Queue;

public class SimpleAppByCdkStack extends Stack {
    public SimpleAppByCdkStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public SimpleAppByCdkStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        final Vpc vpc = new Vpc(this, "vpc"
                , VpcProps.builder().vpcName("simple-app-public-vpc").build());

        final Subnet publicSubnet = new Subnet(this, "public-subnet"
                , SubnetProps.builder().vpcId(vpc.getVpcId()).build());

        final Subnet privateSubnet = new Subnet(this, "private-subnet"
                , SubnetProps.builder().vpcId(vpc.getVpcId()).build());

        final CfnInternetGateway internetGateway = new CfnInternetGateway(this, "internet-gateway"
                , CfnInternetGatewayProps.builder().build());

        publicSubnet.addDefaultInternetRoute(internetGateway.getLogicalId(), publicSubnet);

        // example resource
        // final Queue queue = Queue.Builder.create(this, "SimpleAppByCdkQueue")
        //         .visibilityTimeout(Duration.seconds(300))
        //         .build();
    }
}
