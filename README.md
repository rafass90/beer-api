# beer-api

To install elasticsearch with docker

Pull da imagem do docker:
docker pull docker.elastic.co/elasticsearch/elasticsearch:6.4.2

Iniciar o docker:
docker run -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" docker.elastic.co/elasticsearch/elasticsearch:6.4.2
