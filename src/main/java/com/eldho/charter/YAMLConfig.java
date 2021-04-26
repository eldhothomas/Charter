package com.eldho.charter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class YAMLConfig {

    private String name;
    private String environment;
    private List<String> servers = new ArrayList<>();
    private Spring spring = new Spring();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public List<String> getServers() {
        return servers;
    }

    public void setServers(List<String> servers) {
        this.servers = servers;
    }

    public Spring getSpring() {
        return spring;
    }

    public void setSpring(Spring spring) {
        this.spring = spring;
    }

    public static class Spring {

        private String profiles;
        private Datasource datasource;
        private Jpa jpa;
        private H2 h2;
        

        public String getProfiles() {
            return profiles;
        }

        public void setProfiles(String profiles) {
            this.profiles = profiles;
        }
        
        public H2 getH2() {
        	return h2;
        }
        
        public void setH2(H2 h2) {
        	this.h2 = h2;
        }
         
        public Jpa getJpa() {
        	return jpa;
        }
        
        public void setJpa(Jpa jpa) {
        	this.jpa = jpa;
        }

        public Datasource getDatasource() {
            return datasource;
        }

        public void setDatasource(Datasource datasource) {
            this.datasource = datasource;
        }

        public static class H2 {
        	
        	private Console console;
        	
        	public Console getConsole() {
        		return console;
        	}
        	public void setConsole(Console console) {
        		this.console = console;
        	}
        	
        	public static class Console {
        		private String path;
        		
        		public String getPath() {
        			return path;
        		}
        		public void setPath(String path) {
        			this.path = path;
        		}
        		
        	}
        }
        public static class Jpa {
        	private String databasePlatform;
        	
        	public String getDatabasePlatform() {
        		return databasePlatform;
        	}
        	public void setDatabasePlatform(String databasePlatform) {
        		this.databasePlatform = databasePlatform;
        	}
        }
        public static class Datasource {
            
            private DB01 db01;

            public DB01 getDb01() {
                return db01;
            }

            public void setDb01(DB01 db01) {
                this.db01 = db01;
            }

            
            public static class DB01 {
                private String jdbcUrl;

                public String getJdbcUrl() {
                    return jdbcUrl;
                }

                public void setJdbcUrl(String jdbcUrl) {
                    this.jdbcUrl = jdbcUrl;
                }
            }
        }
    }

}