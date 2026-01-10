run:
	@mvn spring-boot:run

shell:
	@nix-shell server.nix

postgre:
	sudo -u postgres psql

log_postgre:
	sudo su - postgres

initialize_postgre:
	initdb --locale en_US.UTF-8 -D /var/lib/postgres/data

# rgnh55
# y
create_postgre_user:
	createuser --interactive
