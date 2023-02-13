package com.myorg;

import com.sun.tools.javac.util.List;

import software.amazon.awscdk.Tags;
import software.amazon.awscdk.services.ec2.Vpc;
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

        final Vpc vpc = Vpc.Builder.create(this, "public-vpc")
                .vpcName("simple-app-public-vpc")
                .build();

        Tags.of(vpc).add("created_by", "cdk");

        // example resource
        // final Queue queue = Queue.Builder.create(this, "SimpleAppByCdkQueue")
        //         .visibilityTimeout(Duration.seconds(300))
        //         .build();
    }
}
