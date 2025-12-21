{ pkgs ? import <nixpkgs> {} }:

pkgs.mkShell {
	packages = with pkgs; [
		jdk21_headless # doesn't have support for GUI
		maven # latest release or use the mvnw in the folder
	];
	JAVA_HOME = "${pkgs.jdk21_headless}";
	NIX_SHL_LVL = 1;
	shellHook = ''
	'';
}
