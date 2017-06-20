package com.laihan.configuration;

import java.io.File;

import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.store.kahadb.KahaDBPersistenceAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * 启动ActiveMQ
 * @author laiiihan
 *
 */
@Configuration
public class EmbeddedBroker {
	private static final Logger LOG = LoggerFactory.getLogger(EmbeddedBroker.class);
	@Value("${activemq.broker.uri}")
	private String brokerURI;
	private String dbPath = new File(new File(System.getProperty("user.dir")),"store").getAbsolutePath();
	@Bean
	public BrokerService start() throws Exception{
		BrokerService broker= new BrokerService();
		broker.addConnector(brokerURI);
		broker.setPersistent(true);
		broker.setUseJmx(false);
		broker.setUseShutdownHook(true);
		broker.setUseLoggingForShutdownErrors(true);
		broker.setEnableStatistics(true);
		KahaDBPersistenceAdapter persistenceAdapter = new KahaDBPersistenceAdapter();
		persistenceAdapter.setDirectory(new File(dbPath));
		persistenceAdapter.setJournalMaxFileLength(1024*1024*32);//32MB
		persistenceAdapter.setEnableIndexWriteAsync(false);
		persistenceAdapter.setCheckForCorruptJournalFiles(true);
		persistenceAdapter.setChecksumJournalFiles(true);
		broker.setPersistenceAdapter(persistenceAdapter);
	    broker.start(true);
	    LOG.info("ActiveMQ started on "+brokerURI);
	    return broker;
	}
}
