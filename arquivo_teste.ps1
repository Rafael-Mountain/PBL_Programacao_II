# Desativa mensagens de erro padrão
$ErrorActionPreference = 'Stop'

# Obtém o diretório do script atual
$baseDir = Split-Path -Parent $MyInvocation.MyCommand.Definition

# Define o caminho do arquivo de saída
$outputFile = Join-Path $baseDir "resultado.txt"

# Cria ou limpa o arquivo de saída
New-Item -Path $outputFile -ItemType File -Force | Out-Null

# Define o caminho da pasta src
$srcDir = Join-Path $baseDir "src"

# Verifica se a pasta src existe
if (-not (Test-Path $srcDir)) {
    Write-Host 'A pasta "src" não foi encontrada.'
    exit 1
}

# Percorre todos os arquivos na pasta src e salva no arquivo de saída
Get-ChildItem -Path $srcDir -Recurse -File | ForEach-Object {
    $relativePath = $_.FullName.Substring($baseDir.Length).TrimStart('\') -replace '\\', '/'

    Add-Content -Path $outputFile -Value "=========================="
    Add-Content -Path $outputFile -Value "{$relativePath}"
    Get-Content $_.FullName | Add-Content -Path $outputFile
    Add-Content -Path $outputFile -Value "`n=========================`n"
}

Write-Host "O conteúdo foi salvo em `"$outputFile`"."
