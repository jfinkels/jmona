# Apache Commons public repository
repositories.remote << 'https://repository.apache.org/content/groups/public'

# Maven 2 central repository (mirror)
repositories.remote << 'http://www.ibiblio.org/maven2/'

desc 'jmona'
define 'jmona' do
  project.group = 'jmona'
  project.version = '0.5-SNAPSHOT'
  compile.with \
  'log4j:log4j:jar:1.2.14',
  'net.sf.jopt-simple:jopt-simple:jar:3.2',
  'org.apache.commons:commons-lang3:jar:3.0-SNAPSHOT',
  'org.apache.commons:commons-math:jar:2.1',
  transitive('org.springframework:spring-context:jar:3.0.3.RELEASE')
  test.with transitive('org.springframework:spring-test:jar:3.0.3.RELEASE')
  test.include '*AllTest'
  compile.options.deprecation = true
  compile.options.lint = true
  compile.options.source = 1.6
  compile.options.target = 1.6
  compile.options.warnings = true
  package :jar, :id => 'jmona'
end
