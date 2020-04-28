package com.palette;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan(basePackages = "com.palette")
public class PaletteApplication {

	@Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;
	@Value("${cloud.aws.credentials.secretKey}")
	private String secretKey;
	@Value("${cloud.aws.region.static}")
	private String region;

	public static final String APPLICATION_LOCATIONS = "com.palette="
	+ "classpath:application.yml,";

	public static void main(String[] args) {
		new SpringApplicationBuilder(PaletteApplication.class)
		.properties(APPLICATION_LOCATIONS)
		.run(args);
	}

	@Bean
	public AWSCredentials awsCredentials(){
		return new BasicAWSCredentials(accessKey, secretKey);
	}

	@Bean
	public AmazonS3 amazonS3Client(AWSCredentials awsCredentials) {
		AmazonS3 amazonS3Client = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).withRegion(region).build();
    return amazonS3Client;
	}

}
