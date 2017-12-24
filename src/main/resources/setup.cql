CREATE TABLE tweet (id bigint PRIMARY KEY, idStr text, text text, fromUser text);
CREATE INDEX tweetfromuserindex ON tweet (fromuser);
CREATE INDEX tweettextindex ON tweet (text);

CREATE TABLE tag (id bigint PRIMARY KEY, tag text, dateAdded date);

CREATE CUSTOM INDEX tweet_text_sasi_index ON tweet (text) USING 'org.apache.cassandra.index.sasi.SASIIndex' WITH OPTIONS = {'analyzer_class': 'org.apache.cassandra.index.sasi.analyzer.StandardAnalyzer', 'case_sensitive': 'false'};


CREATE CUSTOM INDEX tweet_text_sasi_index ON tweet (text) USING 'org.apache.cassandra.index.sasi.SASIIndex' WITH OPTIONS = {'mode': 'CONTAINS', 'analyzer_class': 'org.apache.cassandra.index.sasi.analyzer.StandardAnalyzer', 'case_sensitive': 'false'};