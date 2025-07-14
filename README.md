# EmployeeManagement
従業員管理機能を作成後、犬管理機能をPHPからリプレイスし追加作成中です。


Get-ChildItem -Path . -Recurse -File -Filter *.txt | ForEach-Object {
    $file = $_.FullName
    $content = Get-Content $file -Raw -Encoding UTF8

    # UPDATE ～ テーブル名 SET まで
    $newContent = [regex]::Replace(
        $content,
        'UPDATE([\s\S]*?\w+\s+SET)',
        { param($m) ($m.Groups[0].Value -replace "`r?`n", " ") }
    )

    Set-Content $file $newContent -Encoding UTF8
}
