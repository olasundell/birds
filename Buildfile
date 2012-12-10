repositories.remote << 'http://www.ibiblio.org/maven2/'
repositories.remote << 'http://repo1.maven.org/maven2/'
repositories.remote << 'https://repository.jboss.org/nexus/content/repositories/releases'
repositories.remote << 'https://repository.jboss.org/nexus/content/repositories/deprecated'
repositories.remote << 'https://repository.jboss.org/nexus/content/repositories/thirdparty-releases'

SPRING = group('spring-core', 'spring-context', 'spring-test', 'spring-webmvc', 'spring-orm', 'spring-beans',
	'spring-tx', 'spring-web', 'spring-asm', 'spring-expression', 'spring-jdbc',
	:under=>"org.springframework", :version=>'3.0.6.RELEASE')

SLF4J = group('slf4j-api', 'slf4j-log4j12',
	:under => "org.slf4j", :version=>'1.5.8')

HIBERNATE = group('hibernate-core', 'hibernate-entitymanager',
				  #:under => 'org.hibernate', :version => '4.1.6.Final')
				  :under => 'org.hibernate', :version => '3.6.10.Final')
				  #:under => 'org.hibernate', :version => '3.6.8.Final')

APACHE_HTTP = group('httpclient','httpcore',
					:under => 'org.apache.httpcomponents', :version => '4.0.1')
#'javax.persistence:persistence-api:jar:1.0.2',
define 'birds' do
	project.version = '0.2-SNAPSHOT'
	test.using :testng
	compile.options.other = ['-Xmaxerrs', '5']
	compile.with 'org.jsoup:jsoup:jar:1.6.1', 'org.testng:testng:jar:6.3.1', transitive('org.mockito:mockito-all:jar:1.8.5'),
				'org.hsqldb:hsqldb:jar:2.2.6', 'jboss:javassist:jar:3.7.ga', 'javax.servlet:servlet-api:jar:2.5',
				'commons-logging:commons-logging:jar:1.1.1',
				'commons-dbcp:commons-dbcp:jar:1.4', 'log4j:log4j:jar:1.2.16', 'javax.transaction:jta:jar:1.1',
				'commons-pool:commons-pool:jar:1.6','org.hibernate.javax.persistence:hibernate-jpa-2.0-api:jar:1.0.1.Final',
				'org.hibernate:hibernate-commons-annotations:jar:3.2.0.Final', 'dom4j:dom4j:jar:1.6.1',
				'org.apache.derby:derby:jar:10.8.2.2', 'commons-collections:commons-collections:jar:3.2.1',
				'antlr:antlr:jar:2.7.7',
				 SPRING, SLF4J, APACHE_HTTP, HIBERNATE
	package(:war)
end